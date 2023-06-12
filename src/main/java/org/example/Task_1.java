//Дана строка sql-запроса "select * from students where ".
//Сформируйте часть WHERE этого запроса, используя StringBuilder.
// Данные для фильтрации приведены ниже в виде json-строки.
//Если значение null, то параметр не должен попадать в запрос.
//Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}

package org.example;

public class Task_1 {
    public static void main(String[] args) {
        String str = "{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}";
        StringBuilder builder = new StringBuilder(str);
        builder.deleteCharAt(builder.indexOf("{"));
        while (builder.indexOf(":") != -1) {
            builder.replace(builder.indexOf(":"), builder.indexOf(":") + 1, "=");
        }
        while (builder.indexOf("\"") != -1){
            builder.replace(builder.indexOf("\""), builder.indexOf("\"") + 1, "");
        }
        for (int i = 0; i < builder.length()-1; i++) {
            if (builder.charAt(i) == '='){
                builder.insert(i+1,'\'');
            }
            if (builder.charAt(i) == ','){
                builder.insert(i,"\'");
                i++;
            }
        }
        builder.insert(builder.indexOf("}"), "\'");
        builder.deleteCharAt(builder.lastIndexOf("}"));
        String[] arrStr = builder.toString().split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arrStr.length; i++){
            if(arrStr[i].contains("null")){
                result.append("");
            }
            else{
                result.append(arrStr[i] + " ");
            }
        }
        result.deleteCharAt(result.lastIndexOf(","));
        String x = result.toString();
        System.out.println("SELECT from * WHERE " + x);
    }
}