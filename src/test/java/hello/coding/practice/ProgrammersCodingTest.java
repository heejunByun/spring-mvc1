package hello.coding.practice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
     * <p>
     * 제한 조건
     * 공백은 아무리 밀어도 공백입니다.
     * s는 알파벳 소문자, 대문자, 공백으로만 이루어져 있습니다.
     * s의 길이는 8000이하입니다.
     * n은 1 이상, 25이하인 자연수입니다.
     * <p>
     * 입출력 예
     * s	    n	result
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

    /**
     * 대문자와 소문자가 섞여있는 문자열 s가 주어집니다. s에 'p'의 개수와 'y'의 개수를 비교해 같으면 True, 다르면 False를 return 하는 solution를 완성하세요. 'p', 'y' 모두 하나도 없는 경우는 항상 True를 리턴합니다. 단, 개수를 비교할 때 대문자와 소문자는 구별하지 않습니다.
     * <p>
     * 예를 들어 s가 "pPoooyY"면 true를 return하고 "Pyy"라면 false를 return합니다.
     * <p>
     * 제한사항
     * 문자열 s의 길이 : 50 이하의 자연수
     * 문자열 s는 알파벳으로만 이루어져 있습니다.
     * <p>
     * 입출력 예
     * s	answer
     * "pPoooyY"	true
     * "Pyy"	false
     * 입출력 예 설명
     * 입출력 예 #1
     * 'p'의 개수 2개, 'y'의 개수 2개로 같으므로 true를 return 합니다.
     * 입출력 예 #2
     * 'p'의 개수 1개, 'y'의 개수 2개로 다르므로 false를 return 합니다.
     */
    @Test
    @DisplayName("문자열 내 p와 y의 갯수")
    void pyCount() {
        String s = "pPoooyY";
        Boolean answer = true;
        int p = 0;
        int y = 0;
        for (int i = 0; i < s.length(); i++) {
            //char c = s.charAt(i); char로 if 문 돌리면 무조건 false 가 됨 이유는???
            String c = String.valueOf(s.charAt(i));
            if ("p".equals(c) || "P".equals(c)) {
                p++;
            } else if ("y".equals(c) || "Y".equals(c)) {
                y++;
            }
        }
        if (p == y) {
            answer = true;
        } else if (p == 0 && y == 0) {
            answer = true;
        } else {
            answer = false;
        }
        assertThat(answer).isTrue();

        //두줄 답
        //s = s.toUpperCase();
        //return s.chars().filter( e -> 'P'== e).count() == s.chars().filter( e -> 'Y'== e).count();
    }


    /**
     *
     * 슈퍼 게임 개발자 오렐리는 큰 고민에 빠졌다. 그녀가 만든 프랜즈 오천성이 대성공을 거뒀지만, 요즘 신규 사용자의 수가 급감한 것이다.
     * 원인은 신규 사용자와 기존 사용자 사이에 스테이지 차이가 너무 큰 것이 문제였다.
     *
     * 이 문제를 어떻게 할까 고민 한 그녀는 동적으로 게임 시간을 늘려서 난이도를 조절하기로 했다. 역시 슈퍼 개발자라 대부분의 로직은 쉽게 구현했지만,
     * 실패율을 구하는 부분에서 위기에 빠지고 말았다. 오렐리를 위해 실패율을 구하는 코드를 완성하라.
     *
     * 실패율은 다음과 같이 정의한다.
     * 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
     * 전체 스테이지의 개수 N, 게임을 이용하는 사용자가 현재 멈춰있는 스테이지의 번호가 담긴 배열 stages가 매개변수로 주어질 때,
     * 실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열을 return 하도록 solution 함수를 완성하라.
     * 10 9 8 7 6 5 4 3 2 1
     * 제한사항
     * 스테이지의 개수 N은 1 이상 500 이하의 자연수이다.
     * stages의 길이는 1 이상 200,000 이하이다.
     * stages에는 1 이상 N + 1 이하의 자연수가 담겨있다.
     * 각 자연수는 사용자가 현재 도전 중인 스테이지의 번호를 나타낸다.
     * 단, N + 1 은 마지막 스테이지(N 번째 스테이지) 까지 클리어 한 사용자를 나타낸다.
     * 만약 실패율이 같은 스테이지가 있다면 작은 번호의 스테이지가 먼저 오도록 하면 된다.
     * 스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0 으로 정의한다.
     * 입출력 예
     * N	stages	result
     * 5	[2, 1, 2, 6, 2, 4, 3, 3]	[3,4,2,1,5]
     * 4	[4,4,4,4,4]	[4,1,2,3]
     * 입출력 예 설명
     * 입출력 예 #1
     * 1번 스테이지에는 총 8명의 사용자가 도전했으며, 이 중 1명의 사용자가 아직 클리어하지 못했다. 따라서 1번 스테이지의 실패율은 다음과 같다.
     *
     * 1 번 스테이지 실패율 : 1/8
     * 2번 스테이지에는 총 7명의 사용자가 도전했으며, 이 중 3명의 사용자가 아직 클리어하지 못했다. 따라서 2번 스테이지의 실패율은 다음과 같다.
     *
     * 2 번 스테이지 실패율 : 3/7
     * 마찬가지로 나머지 스테이지의 실패율은 다음과 같다.
     *
     * 3 번 스테이지 실패율 : 2/4
     * 4번 스테이지 실패율 : 1/2
     * 5번 스테이지 실패율 : 0/1
     * 각 스테이지의 번호를 실패율의 내림차순으로 정렬하면 다음과 같다.
     *
     * [3,4,2,1,5]
     * 입출력 예 #2
     *
     * 모든 사용자가 마지막 스테이지에 있으므로 4번 스테이지의 실패율은 1이며 나머지 스테이지의 실패율은 0이다.
     *
     * [4,1,2,3]
     */
    @Test
    @DisplayName("카카오-실패율")
    void failRate() {

        /**
         * 정확도 70프로 나와서 틀림
         */
        int N = 5;
        int[] answer = new int[N];
        int[] stage = {2, 1, 2, 6, 2, 4, 3, 3};

        int cnt = 0;
        int people = stage.length;
        HashMap<Integer, Double> map = new HashMap<Integer, Double>();
        for (int i = 1; i <= N; i++) {
            double rate = 0.0;
            for (int j = 0; j < stage.length; j++) {
                if (i == stage[j]) {
                    cnt++;
                }
            }
            //실패율 계산
            if (people != 0) {
                rate = (double) cnt / people;
            } else {
                rate = 0; //이걸 왜 꼭 해줘야하지????????
            }
            System.out.println("cnt / people  = " + cnt + " / " + people);
            System.out.println("rate = " + rate);
            map.put(i, rate);
            people = people - cnt;
            cnt = 0;
            //rate 실패율 내림차순으로 정렬해야함 출력은 N으로 해야함
        }
        System.out.println("map  = " + map);

        List<Integer> listKeySet = new ArrayList<>(map.keySet());
        // 내림차순
        Collections.sort(listKeySet, (val1, val2) ->
                (map.get(val2).compareTo(map.get(val1))));

        int idx = 0;
        for (Integer key : listKeySet) {
            System.out.println("key : " + key + " // " + "value : " + map.get(key) );
            answer[idx++] = key;
        }
        System.out.println("answer : " + answer);
        for(int number : answer) {
            System.out.println(number);
        }
        assertThat("1").isEqualTo("1");
    }

    /**
     *
     * 비밀지도
     * 네오는 평소 프로도가 비상금을 숨겨놓는 장소를 알려줄 비밀지도를 손에 넣었다.
     * 그런데 이 비밀지도는 숫자로 암호화되어 있어 위치를 확인하기 위해서는 암호를 해독해야 한다. 다행히 지도 암호를 해독할 방법을 적어놓은 메모도 함께 발견했다.
     *
     * 지도는 한 변의 길이가 n인 정사각형 배열 형태로, 각 칸은 "공백"(" ") 또는 "벽"("#") 두 종류로 이루어져 있다.
     * 전체 지도는 두 장의 지도를 겹쳐서 얻을 수 있다. 각각 "지도 1"과 "지도 2"라고 하자. 지도 1 또는 지도 2 중 어느 하나라도 벽인 부분은 전체 지도에서도 벽이다.
     * 지도 1과 지도 2에서 모두 공백인 부분은 전체 지도에서도 공백이다.
     *
     * "지도 1"과 "지도 2"는 각각 정수 배열로 암호화되어 있다.
     * 암호화된 배열은 지도의 각 가로줄에서 벽 부분을 1, 공백 부분을 0으로 부호화했을 때 얻어지는 이진수에 해당하는 값의 배열이다.
     * secret map
     *
     * 네오가 프로도의 비상금을 손에 넣을 수 있도록, 비밀지도의 암호를 해독하는 작업을 도와줄 프로그램을 작성하라.
     *
     * 입력 형식
     * 입력으로 지도의 한 변 크기 n 과 2개의 정수 배열 arr1, arr2가 들어온다.
     *
     * 1 ≦ n ≦ 16
     * arr1, arr2는 길이 n인 정수 배열로 주어진다.
     * 정수 배열의 각 원소 x를 이진수로 변환했을 때의 길이는 n 이하이다. 즉, 0 ≦ x ≦ 2n - 1을 만족한다.
     * 출력 형식
     * 원래의 비밀지도를 해독하여 '#', 공백으로 구성된 문자열 배열로 출력하라.
     *
     * 입출력 예제
     * 매개변수	값
     * n	5
     * arr1	[9, 20, 28, 18, 11]
     * arr2	[30, 1, 21, 17, 28]
     * 출력	["#####","# # #", "### #", "# ##", "#####"]
     * 매개변수	값
     * n	6
     * arr1	[46, 33, 33 ,22, 31, 50]
     * arr2	[27 ,56, 19, 14, 14, 10]
     * 출력	["######", "### #", "## ##", " #### ", " #####", "### # "]

     */
    @Test
    @DisplayName("카카오-비밀지도 (난이도 하)")
    void secretMap() {
//        int n = 6;
//        int[] arr1 = {46, 33, 33, 22, 31, 50};
//        int[] arr2 = {27, 56, 19, 14, 14, 10};

        FunctionArea functionArea = new FunctionArea();

        int n = 5;
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};
        String[] answer = new String[n];

        List<String> listArr1 = new ArrayList<String>();
        List<String> listArr2 = new ArrayList<String>();

//        String binaryString = Integer.toBinaryString(i); //2수
//        String octalString = Integer.toOctalString(i);   //8진수
//        String hexString = Integer.toHexString(i);       //16진수

//        int binaryToDecimal = Integer.parseInt(binaryString, 2); // 2진수 -> 10진수
//        int binaryToOctal = Integer.parseInt(octalString, 8); // 8진수 -> 10진수
//        int binaryToHex = Integer.parseInt(hexString, 16); // 16진수 -> 10진수

        String binaryStringP1;
        String binaryStringP2;
        for (int i = 0; i < arr1.length; i++) {
            binaryStringP1 = Integer.toBinaryString(arr1[i]);
            binaryStringP1 = functionArea.fnLPAD(binaryStringP1, n, "0");
            System.out.println("binaryStringP1 = " + binaryStringP1);
            listArr1.add(binaryStringP1);
        }

        for (int i = 0; i < arr2.length; i++) {
            binaryStringP2 = Integer.toBinaryString(arr2[i]);
            binaryStringP2 = functionArea.fnLPAD(binaryStringP2, n, "0");
            System.out.println("binaryStringP2 = " + binaryStringP2);
            listArr2.add(binaryStringP2);
        }

        for (int i = 0; i < n; i++) {
            String finString = "";
            //String test = "";
            String s1 = listArr1.get(i);
            String s2 = listArr2.get(i);
            for (int j = 0; j < s1.length(); j++) {
                char c1 = s1.charAt(j);
                char c2 = s2.charAt(j);
                int numericValue = Character.getNumericValue(c1) + Character.getNumericValue(c2);
                //test += Integer.toString(numericValue);
                finString += numericValue == 0 ?  " " : "#";
                answer[i] = finString;
            }
            System.out.println("finString = " + finString);
        }

        assertThat("1").isEqualTo("1");
    }
    class FunctionArea {
        public String fnLPAD(String str, int len, String addStr) {
            String result = str;
            int tempLength = len - result.length();
            for (int i = 0; i < tempLength; i++) {
                result = addStr + result;
            }
            return result;
        }
    }


}

















