package com.excomm.safety;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * Created by liang on 2018/7/13.
 */
public class DesSecrte {
    /**
     * DES算法密钥
     */
    private static final String charset = "UTF-8";
    private static final String algorithm = "DES";

    /**
     * 数据加密，算法（DES）
     * @param data 要进行加密的数据
     * @return 加密后的数据
     */

    public static String encryptBasedDes(String data,byte [] desKey) {
        String encryptedData = null;
        try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(desKey, algorithm);
            Cipher c1 = Cipher.getInstance(algorithm);
            System.out.println(deskey.toString());
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            // 加密，并把字节数组编码成字符串
//            encryptedData = new sun.misc.BASE64Encoder().encode(c1.doFinal(data.getBytes(charset)));

            encryptedData =  Base64.encodeBase64String(c1.doFinal(data.getBytes(charset)));

        } catch (Exception e) {
//            log.error("加密错误，错误信息：", e);
            throw new RuntimeException("加密错误，错误信息：", e);
        }
        return encryptedData;
    }


    /**
     * 数据解密，算法（DES）
     *
     * @param cryptData 加密数据
     * @return 解密后的数据
     */
    public static String decryptBasedDes(String cryptData,byte [] desKey) {
        String decryptedData = null;
        try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(desKey, algorithm);
            //解密
            Cipher c1 = Cipher.getInstance(algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            // 把字符串解码为字节数组，并解密
            decryptedData = new String(c1.doFinal(Base64.decodeBase64(cryptData)), charset);

        } catch (Exception e) {
            // log.error("解密错误，错误信息：", e);
            throw new RuntimeException("解密错误，错误信息：", e);
        }
        return decryptedData;

    }

    /**
     * hexString
     *
     * @param hexString  这个可以双方约定的渠道号和时间，获取初始的DES秘钥，
     *                   下一步的秘钥交换的加密采用此DES 进行加密和解密
     * @return
     */
    public static byte[] asc2bin(String hexString) {
        byte[] hexbyte = hexString.getBytes();
        StringBuffer str = new StringBuffer();
        for(int i=0;i<hexbyte.length;i++){
            System.out.println(hexbyte[i]);
            str.append(hexbyte[i]);
        }

        System.out.println("str=="+str+"  长度："+hexbyte.length+"  str长度："+str.length());

        byte[] bitmap = new byte[hexbyte.length / 2];
        for (int i = 0; i < bitmap.length; i++) {
            int k = hexbyte[i * 2] > '9' ? 7 : 0;
            System.out.println("kk=="+k);
            hexbyte[i * 2] -= hexbyte[i * 2] > '9' ? 7 : 0;
            hexbyte[i * 2 + 1] -= hexbyte[i * 2 + 1] > '9' ? 7 : 0;
            bitmap[i] = (byte) ((hexbyte[i * 2] << 4 & 0xf0) | (hexbyte[i * 2 + 1] & 0x0f));
        }
        return bitmap;

//        byte[] hexbyte = hexString.getBytes();
//        byte[] bitmap = new byte[hexbyte.length / 2];
//        for (int i = 0; i < bitmap.length; i++) {
//            hexbyte[i * 2] -= hexbyte[i * 2] > '9' ? 7 : 0;
//            hexbyte[i * 2 + 1] -= hexbyte[i * 2 + 1] > '9' ? 7 : 0;
//            bitmap[i] = (byte) ((hexbyte[i * 2] << 4 & 0xf0) | (hexbyte[i * 2 + 1] & 0x0f));
//        }
//        return bitmap;
    }


    public static void main(String args[]){


        String name = "中国水电费水电费";
//        byte[] DES_KEY = {21, 1, -110, 82, -32, -85, -128, -65};
        byte[] DES_KEY;

//        String key ="0011womewomewome";
        //时间采用8位的年月日如20181015 + 8位的渠道号（渠道号必须为一）
        String key ="2018101600000001";
        System.out.println(key.length()+"=长度");
        DES_KEY = asc2bin(key);

        System.out.println("长度=="+DES_KEY.length+"=="+DES_KEY);
        System.out.println(DesSecrte.encryptBasedDes(name,DES_KEY)+"----"
                +DesSecrte.decryptBasedDes(DesSecrte.encryptBasedDes(name,DES_KEY),DES_KEY));


    }

}

