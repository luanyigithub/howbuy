/**
 * 
 */
package com.howbuy.trade.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jiong.peng
 * 
 */
public class CoopSecurityUtil {

	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";
	public static final String DIGEST_ALGORITHM = "SHA-512";

	/** */
	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 245;

	/** */
	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 256;

	final static Logger logger = LoggerFactory.getLogger(CoopSecurityUtil.class);

	private static final String transformation = "RSA/ECB/PKCS1Padding";

	/**
	 * 加载指定路径证书文件，获取公钥
	 * 
	 * @param keyPath
	 *            证书文件路径
	 * @return 公钥对象
	 * @throws RuntimeException
	 */
	public static PublicKey loadPublicKey(String keyPath)
			throws RuntimeException {
		X509Certificate cert = certDispose(keyPath);
		PublicKey key = cert.getPublicKey();
		return key;
	}

	/**
	 * 获取私钥
	 * 
	 * @param filePath
	 * @param passwd
	 * @return
	 * @throws SecurityException
	 */
	public static PrivateKey loadPrivateKey(String filePath, String passwd)
			throws SecurityException {
		PrivateKey key = readPfx2Cert(filePath, passwd);
		return key;
	}
	/**
	 * 读取私钥证书文件
	 * @param file
	 * @param passwd
	 * @return
	 * @throws SecurityException
	 */
	@SuppressWarnings("rawtypes")
	private static PrivateKey readPfx2Cert(String file, String passwd)
			throws SecurityException {
		// Security.addProvider(new BouncyCastleProvider());
		char[] keyStorePd = null;
		if (null != passwd) {
			keyStorePd = passwd.toCharArray();
		}

		String s1 = "";
		InputStream input = null;
		try {
			input = new BufferedInputStream(new FileInputStream(file));
			KeyStore keystore = KeyStore.getInstance("PKCS12");
			keystore.load(input, keyStorePd);
			Enumeration enumeration = keystore.aliases();
			int i;
			for (i = 0; enumeration.hasMoreElements(); i++) {
				s1 = enumeration.nextElement().toString();
			}

			if (i != 1) {
				System.out.println("pfx存在多个私钥");
			}

			return (java.security.PrivateKey) keystore.getKey(s1, keyStorePd);
		} catch (Exception e) {
			throw new SecurityException("read pfx error", e);
		} finally {
			if (null != input) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 转换私钥
	 * 
	 * @param privateKey
	 *            base64私钥字符串
	 * @return 私钥对象
	 */
	public static PrivateKey toPrivateKey(String privateKey) {
		byte[] data = Base64.decodeBase64(privateKey);
		PKCS8EncodedKeySpec pkcs8Enc = new PKCS8EncodedKeySpec(data);

		KeyFactory keyFactory = null;
		PrivateKey priKey = null;
		try {
			keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			priKey = keyFactory.generatePrivate(pkcs8Enc);
		} catch (NoSuchAlgorithmException e) {
			logger.error("异常", e);
		} catch (InvalidKeySpecException e) {
			logger.error("异常", e);
		}

		return priKey;
	}

	/**
	 * 转换公钥
	 * 
	 * @param publicKeyString
	 *            base64公钥字符串
	 * @return 公钥对象
	 */
	public static PublicKey toPublicKey(String publicKeyString) {
		byte[] data = Base64.decodeBase64(publicKeyString);
		X509EncodedKeySpec x509Enc = new X509EncodedKeySpec(data);

		KeyFactory keyFactory = null;
		PublicKey publicKey = null;
		try {
			keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			publicKey = keyFactory.generatePublic(x509Enc);
		} catch (NoSuchAlgorithmException e) {
			logger.error("异常", e);
		} catch (InvalidKeySpecException e) {
			logger.error("异常", e);
		}
		return publicKey;
	}

	/**
	 * 用私钥对信息生成数字签名
	 * 
	 * @param data
	 *            加签数据
	 * @param privateKey
	 *            私钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {

		// 计算明文摘要
		byte[] digestData = digest(data).getBytes("utf-8");
		// 取私钥匙对象
		PrivateKey priKey = toPrivateKey(privateKey);

		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(priKey);
		signature.update(digestData);

		return encryptBASE64(signature.sign());
	}

	/**
	 * 校验数字签名
	 * 
	 * @param data
	 *            加签数据
	 * @param publicKey
	 *            公钥
	 * @param sign
	 *            数字签名
	 * 
	 * @return 校验成功返回true 失败返回false
	 * @throws Exception
	 * 
	 */
	public static boolean verify(byte[] data, String publicKey, String sign)
			throws Exception {
		// 计算明文摘要
		byte[] digestData = digest(data).getBytes("utf-8");
		// 取公钥匙对象
		PublicKey pubKey = toPublicKey(publicKey);

		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(pubKey);
		signature.update(digestData);

		// 验证签名是否正常
		return signature.verify(decryptBASE64(sign));
	}

	/** */
	/**
	 * <p>
	 * 公钥加密
	 * </p>
	 * 
	 * @param data
	 *            源数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String publicKey)
			throws Exception {
		Key publicK = toPublicKey(publicKey);
		return encrypt(publicK, data);
	}

	/** */
	/**
	 * <P>
	 * 私钥解密
	 * </p>
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] encryptedData,
			String privateKey) throws Exception {
		Key privateK = toPrivateKey(privateKey);
		return decrypt(privateK, encryptedData);
	}

	/**
	 * 读取X509证书
	 * 
	 * @param file
	 * @return
	 * @throws SecurityException
	 */
	private static X509Certificate certDispose(String file)
			throws SecurityException {
		InputStream input = null;
		X509Certificate x509certificate;
		try {
			input = new BufferedInputStream(new FileInputStream(file));
			CertificateFactory certificatefactory = CertificateFactory
					.getInstance("X.509");

			x509certificate = (X509Certificate) certificatefactory
					.generateCertificate(input);

			if (null != input)
				try {
					input.close();
				} catch (IOException e) {
					logger.error("异常", e);
				}
		} catch (Exception e) {
			throw new SecurityException("读取证书失败", e);
		} finally {
			if (null != input) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error("异常", e);
				}
			}
		}
		return x509certificate;
	}

	/**
	 * 加密
	 * 
	 * @param key
	 *            密钥
	 * @param data
	 *            源数据bytes
	 * @return
	 * @throws RuntimeException
	 */
	public static byte[] encrypt(Key key, byte[] data) throws Exception {
		// 对数据加密
		Cipher cipher = Cipher.getInstance(transformation);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;

	}

	/**
	 * 解密
	 * 
	 * @param key
	 *            密钥
	 * @param data
	 *            需要解密的bytes
	 * @return
	 * @throws RuntimeException
	 */
	public static byte[] decrypt(Key key, byte[] data) throws Exception {
		Cipher cipher = Cipher.getInstance(transformation);
		cipher.init(Cipher.DECRYPT_MODE, key);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher
						.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher
						.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}



	/**
	 * 生成签名
	 * 
	 * @param data
	 *            源数据bytes
	 * @param prikey
	 *            私钥
	 * @return 签名bytes
	 * @throws RuntimeException
	 */
	public static byte[] generateSignature(byte[] data, PrivateKey prikey)
			throws RuntimeException {
		try {
			// 计算明文摘要
			byte[] digestData = digest(data).getBytes("utf-8");
			Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
			sig.initSign(prikey);
			sig.update(digestData);
			return sig.sign();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		} catch (SignatureException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 验签
	 * 
	 * @param data
	 *            源数据bytes
	 * @param pubKey
	 *            公钥
	 * @param signature
	 *            签名bytes
	 * @return 验签结果
	 * @throws RuntimeException
	 */
	public static boolean verifySignature(byte[] data, PublicKey pubKey,
			byte[] signature) throws RuntimeException {
		try {

			// 计算明文摘要
			byte[] digestData = digest(data).getBytes("utf-8");
			Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);

			sig.initVerify(pubKey);

			sig.update(digestData);

			return sig.verify(signature);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		} catch (SignatureException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Base64解码
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return Base64.decodeBase64(key);
	}

	/**
	 * BASE64编码
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return Base64.encodeBase64String(key);
	}

	/**
	 * 用SHA-512算法计算摘要
	 * 
	 * @param contents
	 * @return
	 * @throws Exception
	 */
	public static String digest(byte[] contents) throws Exception {
		MessageDigest messageDigest = MessageDigest
				.getInstance(DIGEST_ALGORITHM);
		byte[] digestbyte = messageDigest.digest(contents);
		return new String(Hex.encodeHex(digestbyte));
	}

	public static void main(String[] args) throws Exception {

		PrivateKey howubyPriKey = CoopSecurityUtil.loadPrivateKey("E:/temp/coop_test_key/howbuy_private.pfx", "howbuytest");
		PublicKey howbuyPubKey = CoopSecurityUtil.loadPublicKey("E:/temp/coop_test_key/howbuy_public.crt");

		PrivateKey merchantPriKey = CoopSecurityUtil.loadPrivateKey("E:/temp/coop_test_key/merchant_private.pfx", "merchanttest");
		PublicKey merchantPubKey = CoopSecurityUtil.loadPublicKey("E:/temp/coop_test_key/merchant_public.crt");

		
		
		System.out.println("howubyPriKey:" + Base64.encodeBase64String(howubyPriKey.getEncoded()));
		System.out.println("howbuyPubKey:" + Base64.encodeBase64String(howbuyPubKey.getEncoded()));

		System.out.println("merchantPriKey:" + Base64.encodeBase64String(merchantPriKey.getEncoded()));
		System.out.println("merchantPubKey:" + Base64.encodeBase64String(merchantPubKey.getEncoded()));

		String data = "加密测试数据";
		System.out.println("data:" + data);

		//加密
		byte[] encryptData = CoopSecurityUtil.encrypt(howbuyPubKey, data.getBytes("UTF-8"));
		System.out.println("encryptData:" + Base64.encodeBase64String(encryptData));
		
		
		
		
		// 加签
		byte[] signature = CoopSecurityUtil.generateSignature(encryptData,merchantPriKey);
		System.out.println("sign:" + Base64.encodeBase64String(signature));
		
		
		
		//验签
		System.out.println("sign status:" + CoopSecurityUtil.verifySignature(encryptData, merchantPubKey, signature));

		// 解密
		byte[] decryptData = CoopSecurityUtil.decrypt(howubyPriKey,encryptData);
		System.out.println("decryptData:" + new String(decryptData, "UTF-8"));
		
		

	}

}
