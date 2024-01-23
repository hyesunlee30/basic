package com.encore.basic.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Hello {
    private String name;
    private String email;
    private String password;

    public Hello(MyBuilder myBuilder) {
        this.name = myBuilder.name;
        this.email =myBuilder.email;
        this.password =myBuilder.password;
    }

    public static MyBuilder builder() {
        return new MyBuilder();
    }
    //최초값 null
    //변수값들이 셋팅이 안되어 있는데 호출되서 넣으면 셋팅된다

    public static class MyBuilder{
        private String name;
        private String email;
        private String password;

        public MyBuilder name(String name) {
            this.name =name;
            return this;
        }

        public MyBuilder email(String email) {
            this.email =email;
            return this;
        }

        public MyBuilder password(String password) {
            this.password =password;
            return this;
        }

        public Hello build() {
            return new Hello(this);
        }
    }

    @Override
    public String toString() {
        return "Hello{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
