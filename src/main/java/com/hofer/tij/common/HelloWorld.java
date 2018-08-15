package com.hofer.tij.common;

/**
 * @author hofer.bhf
 * created on 2018/05/19 5:28 PM
 */
public class HelloWorld {
    public static void main(String[] args) {
        /**
         * http://baike.corp.taobao.com/index.php/MemberPlatform/userTagWiki#promotedType
         * userTag3 2的49次方 标记此用户是否为分销平台的供应商
         */
        final long SUPPLIER = 1L << 49;

        /**
         * userTag4 2的44次方 标记此用户是分销平台的品牌商
         */
        final long BRAND_SELLER = 1L << 44;
        long userTag3 = 72691599965995136L;
        long userTag4 = 1166511468326421504L;
        System.out.println((userTag3 & SUPPLIER) == SUPPLIER);
        System.out.println((userTag4 & BRAND_SELLER) == BRAND_SELLER);
        boolean isSupplier = ((userTag3 & SUPPLIER) == SUPPLIER) || ((userTag4 & BRAND_SELLER) == BRAND_SELLER);
    }
}
