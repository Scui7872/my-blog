package com.scs.blog.util;

import cn.hutool.crypto.digest.DigestUtil;

import java.time.LocalDate;
import java.util.Random;

public class DataUtil {
    public static String getMobile() {
        StringBuilder stringBuilder = new StringBuilder("132");
        Random random = new Random();
        for (int i = 0;i<8;i++) {
            int num = random.nextInt(10);
            stringBuilder.append(num);
        }
        return stringBuilder.toString();
    }
    public static void main(String[] args) {
        for (int i=0;i<10;i++) {
            System.out.println(getBirthday());
        }
    }

    public static String getPassword() {
        StringBuilder password = new StringBuilder("");
        Random random = new Random();
        for (int i = 0;i<6;i++) {
            int num = random.nextInt(10);
            password.append(num);
        }
        return DigestUtil.md5Hex(password.toString());
    }

    public static String getGender() {
        String [] genders = new String[]{"男","女"};
        Random random = new Random();
        int index = random.nextInt(2);
        return genders[index];
    }

    public static LocalDate getBirthday() {
        LocalDate now = LocalDate.now();
        Random random = new Random();
        int bound = random.nextInt(8888);
        return now.minusDays(bound);
    }

}
