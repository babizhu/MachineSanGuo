package com.bbz.util.common;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 各种数据类型之间的转换都集中在此类中
 * 用返回值来进行区分，比如把一个整型数组转成一个Array就应该在Array静态类中，而把此整型数组转换为List则应该放在List中
 */
public class TransForm{
    public final static class ArrayType{
        /**
         * 把用"逗号"分隔的字符串转换为整型数组，例如：<br/>
         * String s = "1,2,3,4,5,6,7,8,9" 输出整型数组[1, 2, 3, 4, 5, 6, 7, 8, 9]<br/>
         *
         * @param input 用"逗号"分隔的字符串
         * @return 返回的整型数组，如果输入字符串长度为0，返回一个长度为0的数组
         */
        public static int[] toInt( String input ){
            if( input == null ) {

                throw new IllegalArgumentException();
            }
            if( input.isEmpty() ) {
                return new int[0];
            }
            String[] arr = input.split( "," );
            int ret[] = new int[arr.length];
            for( int i = 0; i < arr.length; i++ ) {
                ret[i] = Integer.parseInt( arr[i] );
            }
            return ret;
        }

    }

    public static class StringType{
        /**
         * 把一个整型数组转换为用"逗号"分隔的字符串，例如：<br/>
         * [1, 2, 3, 4, 5, 6, 7, 8, 9] 输出"1,2,3,4,5,6,7,8,9"<br/>
         *
         * @param input 整型数组
         * @return 返回的字符串，如果输入长度为0，怎返回空字符串
         */
        public static String toStr( int[] input ){
            if( input == null ) {
                throw new IllegalArgumentException();
            }
            if( input.length == 0 ) {
                return "";
            }
            StringBuilder ret = new StringBuilder();
            for( int i : input ) {
                ret.append( i ).append( "," );
            }
            return ret.deleteCharAt( ret.length() - 1 ).toString();
        }

        /**
         * 把一个整型List转换为用"逗号"分隔的字符串，例如：<br/>
         * [1, 2, 3, 4, 5, 6, 7, 8, 9] 输出"1,2,3,4,5,6,7,8,9"<br/>
         *
         * @param input 整型列表
         * @return 返回的字符串，如果输入长度为0，怎返回空字符串
         */
        public static String toStr( List<Integer> input ){
            if( input == null ) {
                throw new IllegalArgumentException();
            }
            if( input.size() == 0 ) {
                return "";
            }
            StringBuilder ret = new StringBuilder();
            for( int i : input ) {
                ret.append( i ).append( "," );
            }
            return ret.deleteCharAt( ret.length() - 1 ).toString();
        }

    }

    public static class ListType{
        /**
         * 把用"逗号"分隔的字符串转换为一个List，例如：<br/>
         * String s = "1,2,3,4,5,6,7,8,9" 输出整型List<Integer>[1, 2, 3, 4, 5, 6, 7, 8, 9]<br/>
         *
         * @param input 用"逗号"分隔的字符串
         * @return 返回的整型List，如果输入字符串长度为0，返回一个长度为0的List
         */
        public static List<Integer> toList( String input ){
            if( input == null ) {
                throw new IllegalArgumentException();
            }
            List<Integer> ret = Lists.newArrayList();
            if( input.isEmpty() ) {
                return ret;
            }
            String[] arr = input.split( "," );
            for( String anArr : arr ) {
                ret.add( Integer.parseInt( anArr ) );
            }
            return ret;
        }

        /**
         * 把list的内容打乱，输入的字符串内容将会被改变
         *
         * @param input list
         */
        public static <T> void mix( List<T> input ){
            if( input == null ) {
                throw new IllegalArgumentException();
            }
            int size = input.size();
            if( size < 2 ) {
                return;
            }
            int mixCount = size;
            for( int i = 0; i < mixCount; i++ ) {
                int randomIndex = RandomUtil.getInt( size );
                T temp = input.get( randomIndex );
                input.set( randomIndex, input.get( i ) );
                input.set( i, temp );
            }
        }

    }

    public static void main( String[] args ){
    }
}
