package hello.coding.practice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProgrammersCodingTest {
    /**
     * 새로 생긴 놀이기구는 인기가 매우 많아 줄이 끊이질 않습니다. 이 놀이기구의 원래 이용료는 price원 인데,
     * 놀이기구를 N 번 째 이용한다면 원래 이용료의 N배를 받기로 하였습니다. 즉, 처음 이용료가 100이었다면 2번째에는 200, 3번째에는 300으로 요금이 인상됩니다.
     * 놀이기구를 count번 타게 되면 현재 자신이 가지고 있는 금액에서 얼마가 모자라는지를 return 하도록 solution 함수를 완성하세요.
     * 단, 금액이 부족하지 않으면 0을 return 하세요.
     * <p>
     * 제한사항
     * 놀이기구의 이용료 price : 1 ≤ price ≤ 2,500, price는 자연수
     * 처음 가지고 있던 금액 money : 1 ≤ money ≤ 1,000,000,000, money는 자연수
     * 놀이기구의 이용 횟수 count : 1 ≤ count ≤ 2,500, count는 자연수
     * 입출력 예
     * price	money	count	result
     * 3	    20	    4	    10
     * 입출력 예 설명
     * 입출력 예 #1
     * 이용금액이 3인 놀이기구를 4번 타고 싶은 고객이 현재 가진 금액이 20이라면, 총 필요한 놀이기구의 이용 금액은 30 (= 3+6+9+12) 이 되어 10만큼 부족하므로 10을 return 합니다.
     */
    @Test
    @DisplayName("부족한 금액 계산하기")
    void insufficientAmount() {

        int price = 3;
        int money = 20;
        int count = 4;
        int result = 0;
        int total = 0;

        for (int i = 1; i <= count; i++) {
            total += price * i;
        }

        if (total > money) {
            result = total - money;
        }
        assertThat(result).isEqualTo(10);
    }

    /**
     * 자연수 N이 주어지면, N의 각 자릿수의 합을 구해서 return 하는 solution 함수를 만들어 주세요.
     * 예를들어 N = 123이면 1 + 2 + 3 = 6을 return 하면 됩니다.
     * <p>
     * 제한사항
     * N의 범위 : 100,000,000 이하의 자연수
     * 입출력 예
     * N	answer
     * 123	6
     * 987	24
     */
    @Test
    @DisplayName("자릿수 더하기")
    void locationAdd() {
        int n = 987;
        int result = 0;
        String local;
        local = Integer.toString(n);
        for (int i = 0; i < local.length(); i++) {
            System.out.println("local.substring(i) = " + local.substring(i, i + 1));
            result += Integer.parseInt(local.substring(i, i + 1));
        }

        assertThat(result).isEqualTo(24);
    }

    /**
     * String형 배열 seoul의 element중 "Kim"의 위치 x를 찾아, "김서방은 x에 있다"는 String을 반환하는 함수, solution을 완성하세요.
     * seoul에 "Kim"은 오직 한 번만 나타나며 잘못된 값이 입력되는 경우는 없습니다.
     * <p>
     * 제한 사항
     * seoul은 길이 1 이상, 1000 이하인 배열입니다.
     * seoul의 원소는 길이 1 이상, 20 이하인 문자열입니다.
     * "Kim"은 반드시 seoul 안에 포함되어 있습니다.
     * <p>
     * 입출력 예
     * seoul	        return
     * ["Jane", "Kim"]	"김서방은 1에 있다"
     */
    @Test
    @DisplayName("서울에서 김서방 찾기")
    void searchKim() {
        String[] seoul = {"Jane", "Kim", "Byun"};
        String answer = "";
        String k = "";
        for (int i = 0; i < seoul.length; i++) {
            if ("Kim".equals(seoul[i])) {
                k = String.valueOf(i);
            }
        }
        answer = "김서방은 " + k + "에 있다";
        assertThat(answer).isEqualTo("김서방은 1에 있다");

        //다른사람풀이
        //int x = Arrays.asList(seoul).indexOf("Kim");
        //return "김서방은 "+ x + "에 있다";
    }

    /**
     * 어떤 문장의 각 알파벳을 일정한 거리만큼 밀어서 다른 알파벳으로 바꾸는 암호화 방식을 시저 암호라고 합니다.
     * 예를 들어 "AB"는 1만큼 밀면 "BC"가 되고, 3만큼 밀면 "DE"가 됩니다. "z"는 1만큼 밀면 "a"가 됩니다.
     * 문자열 s와 거리 n을 입력받아 s를 n만큼 민 암호문을 만드는 함수, solution을 완성해 보세요.
     *
     * 제한 조건
     * 공백은 아무리 밀어도 공백입니다.
     * s는 알파벳 소문자, 대문자, 공백으로만 이루어져 있습니다.
     * s의 길이는 8000이하입니다.
     * n은 1 이상, 25이하인 자연수입니다.
     *
     * 입출력 예
     *  s	    n	result
     * "AB" 	1	"BC"
     * "z"	    1	"a"
     * "a B z"	4	"e F d"
     */
    @Test
    @DisplayName("시저 암호")
    void caesarPassword() {
        //못품 ASCII 코드 알아야함
        String s = "a B z";
        int n = 4;

        //26
        String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        //String을 char로 빼서? 빈공간은 무시?

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (int j = 0; j < alphabet.length; j++) {
            }
        }
    }
}

















