package com.xiaokaige.constructFunc;

/**
 * @author: zk
 * Date: 2022/1/28
 * Time: 10:38
 */
public class InnerFunc {
    private static boolean flag = true;

    public static class innerClass{
        public void test(){
            if(flag){
                System.out.println("测试一下下");
            }
        }
    }

    public static void main(String[] args) {
        innerClass innerClass = new innerClass();
        innerClass.test();
        InnerFunc innerFunc = new InnerFunc();
        innerFunc.test02();

        InterfaceTest interfaceTest = new InterfaceTest() {
            @Override
            public void testFunc() {
                System.out.println("再次测试");
            }
        };
        interfaceTest.testFunc();
    }

    public void test02(){
        class AreaInnerClass{
            private String name;

            private Integer age;

            @Override
            public String toString() {
                return "AreaInnerClass{" +
                        "name='" + name + '\'' +
                        ", age=" + age +
                        '}';
            }
        }
        AreaInnerClass areaInnerClass = new AreaInnerClass();
        System.out.println("areaInnerClass = " + areaInnerClass);
    }




}
