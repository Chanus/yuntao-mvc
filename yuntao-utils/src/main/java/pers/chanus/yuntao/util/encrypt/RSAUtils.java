/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util.encrypt;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * RSA(非对称加密)加密解密工具类
 * 
 * @author Chanus
 * @date 2018-08-30 15:33:57
 * @since 0.0.1
 */
public class RSAUtils {
	/**
	 * 加密算法RSA
	 */
	private static final String KEY_ALGORITHM = "RSA";

	/**
	 * 签名算法
	 */
	private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * 获取公钥的key
	 */
	public static final String PUBLIC_KEY = "RSAPublicKey";

	/**
	 * 获取私钥的key
	 */
	public static final String PRIVATE_KEY = "RSAPrivateKey";

	/**
	 * 生成密钥对(公钥和私钥)
	 * 
	 * @return RSA密钥对，由Base64编码，{@code PUBLIC_KEY}对应公钥，{@code PRIVATE_KEY}对应私钥
	 * @since 0.0.1
	 */
	public static Map<String, String> generateKeyPair() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			keyPairGenerator.initialize(1024);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			Map<String, String> keyMap = new HashMap<String, String>(2);
			keyMap.put(PUBLIC_KEY, Base64.getEncoder().encodeToString(publicKey.getEncoded()));
			keyMap.put(PRIVATE_KEY, Base64.getEncoder().encodeToString(privateKey.getEncoded()));
			return keyMap;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("NoSuchAlgorithmException occurred.", e);
		}
	}

	/**
	 * 公钥加密
	 * 
	 * @param data	源数据
	 * @param publicKey	公钥(BASE64编码)
	 * @return 公钥加密后的数据
	 * @since 0.0.1
	 */
	public static byte[] encryptByPublicKey(byte[] data, String publicKey) {
		try {
			byte[] keyBytes = Base64.getDecoder().decode(publicKey);
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key key = keyFactory.generatePublic(x509EncodedKeySpec);
			// 对数据加密
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, key);
			int dataLength = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段加密
			while (dataLength - offSet > 0) {
				if (dataLength - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(data, offSet, dataLength - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.close();
			return encryptedData;
		} catch (Exception e) {
			throw new RuntimeException("encrypt by public key error.", e);
		}
	}

	/**
	 * 私钥加密
	 * 
	 * @param data	源数据
	 * @param privateKey	私钥(BASE64编码)
	 * @return 私钥加密后的数据
	 * @since 0.0.1
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String privateKey) {
		try {
			byte[] keyBytes = Base64.getDecoder().decode(privateKey);
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key key = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, key);
			int dataLength = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段加密
			while (dataLength - offSet > 0) {
				if (dataLength - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(data, offSet, dataLength - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.close();
			return encryptedData;
		} catch (Exception e) {
			throw new RuntimeException("encrypt by private key error.", e);
		}
	}

	/**
	 * 公钥解密
	 * 
	 * @param data	已加密数据
	 * @param publicKey	公钥(BASE64编码)
	 * @return 公钥解密后的数据
	 * @since 0.0.1
	 */
	public static byte[] decryptByPublicKey(byte[] data, String publicKey) {
		try {
			byte[] keyBytes = Base64.getDecoder().decode(publicKey);
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key key = keyFactory.generatePublic(x509EncodedKeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, key);
			int dataLength = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (dataLength - offSet > 0) {
				if (dataLength - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(data, offSet, dataLength - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return decryptedData;
		} catch (Exception e) {
			throw new RuntimeException("decrypt by public key error.", e);
		}
	}

	/**
	 * 私钥解密
	 * 
	 * @param data	已加密数据
	 * @param privateKey	私钥(BASE64编码)
	 * @return 私钥解密后的数据
	 * @since 0.0.1
	 */
	public static byte[] decryptByPrivateKey(byte[] data, String privateKey) {
		try {
			byte[] keyBytes = Base64.getDecoder().decode(privateKey);
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key key = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, key);
			int dataLength = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (dataLength - offSet > 0) {
				if (dataLength - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(data, offSet, dataLength - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return decryptedData;
		} catch (Exception e) {
			throw new RuntimeException("decrypt by private key error.", e);
		}
	}

	/**
	 * 用私钥对信息生成数字签名
	 * 
	 * @param data	已加密数据
	 * @param privateKey	私钥(BASE64编码)
	 * @return 数字签名
	 * @since 0.0.1
	 */
	public static String sign(byte[] data, String privateKey) {
		try {
			byte[] keyBytes = Base64.getDecoder().decode(privateKey);
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			PrivateKey key = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(key);
			signature.update(data);
			return new String(Base64.getEncoder().encode(signature.sign()));
		} catch (Exception e) {
			throw new RuntimeException("generate sign error.", e);
		}
	}

	/**
	 * 校验数字签名
	 * 
	 * @param data	已加密数据
	 * @param publicKey	公钥(BASE64编码)
	 * @param sign	数字签名
	 * @return {@code true} 校验数字签名成功；{@code false} 校验数字签名失败
	 * @since 0.0.1
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) {
		try {
			byte[] keyBytes = Base64.getDecoder().decode(publicKey);
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			PublicKey key = keyFactory.generatePublic(x509EncodedKeySpec);
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initVerify(key);
			signature.update(data);
			return signature.verify(Base64.getDecoder().decode(sign));
		} catch (Exception e) {
			throw new RuntimeException("verify sign error.", e);
		}
	}
}
