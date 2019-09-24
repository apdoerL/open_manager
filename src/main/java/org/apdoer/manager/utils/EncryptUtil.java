package org.apdoer.manager.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.net.URLCodec;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Slf4j
public class EncryptUtil {

    /***
     * 将url进行encode
     * @param url
     * @return
     */
    public static String urlEncode(String url) {
        URLCodec codec = new URLCodec();
        try {
            return codec.encode(url);
        } catch (EncoderException e) {
			log.error("Error at urlEncode", e);
            return null;
        }
    }

    /***
     * 将url进行decode
     * @param url
     * @return
     */
    public static String urlDecode(String url) {
        URLCodec codec = new URLCodec();
        try {
            return codec.decode(url);
        } catch (DecoderException e) {
			log.error("Error at urlDecode", e);
            return null;
        }
    }

    public static String utf8Enode(String s) {
        String res;
        try {
            res = URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
			log.error("Error at utf8Enode", e);
            return null;
        }
        return res;
    }

    /***
     * HmacSha256签名,进行base64编码
     * @param signStr
     * @param secretKey
     * @return
     */
    public static String getHmacHash(String signStr, String secretKey) {
        String hash;
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            hash = Base64.encodeBase64String(sha256_HMAC.doFinal(signStr.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
			log.error("Error at getHmacHash", e);
            return "";
        }
        return hash;
    }


    public static String myMD5(String strSrc) {
        MessageDigest md = null;
        String encStr = null;

        byte[] bt = strSrc.getBytes(StandardCharsets.UTF_8);
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(bt);
            encStr = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
			log.error("Error at myMD5", e);
        }
        return encStr;
    }

    /**
     * 转换为16进制字符
     **/
    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
    
    /**
     *  AES加密
     * */
    public static String encrypt(String sSrc) throws Exception {
        String sKey = "hhhhhhhhhhhhhhhh"; // 随机密锁
        byte[] raw = sKey.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes(StandardCharsets.UTF_8));// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("UTF-8"));
        return Base64.encodeBase64String(encrypted);// 此处使用BAES64做转码功能，同时能起到2次加密的作用。
    }

    /**
     * AES解密
     **/
    public static String decrypt(String sSrc) throws Exception {
    	String sKey = "hhhhhhhhhhhhhhhh";  // 随机密锁
        byte[] raw = sKey.getBytes("ASCII");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec("0102030405060708"
                .getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] encrypted1 = Base64.decodeBase64(sSrc);// 先用bAES64解密
        //System.out.println(encrypted1.length);
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original,StandardCharsets.UTF_8);
        return originalString;
    }
    
    //生成随机密锁
    private static String getKey(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        // 参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                sb.append((char) (random.nextInt(26) + temp));
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                sb.append(String.valueOf(random.nextInt(10)));
            }
        }

        try {
            return new String(sb.toString().getBytes(StandardCharsets.UTF_8), "UTF-8");
        } catch (UnsupportedEncodingException e) {
			log.error("Error at getKey", e);
        }
        return "newex_secret_key";
    }

}
