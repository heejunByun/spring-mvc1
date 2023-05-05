package hello.coding.practice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

//비교적 쉬운 문제들
public class ProgrammersCodingTestLevel1 {

    @Test
    @DisplayName("정수 제곱근 판별")
    void numberRoute() {
        // 제곱근 구하는 함수 Math.sqrt(double a);
        double sqrt = Math.sqrt(121);
        System.out.println("sqrt = " + sqrt);
        double sqrt1 = Math.sqrt(3);
        System.out.println("sqrt1 = " + sqrt1);

        long answer;
        if (Math.ceil(sqrt) == Math.floor(sqrt)) { // 숫자형이 정수인지 실수인지 판단하는 방법 소수점 버림과 올림이 같은 수 일경우 정수임
            sqrt = sqrt + 1;
            answer = (long) (sqrt * sqrt);
        } else {
            answer = -1;
        }
        assertThat(answer).isEqualTo(144);
    }

    /**
     * charAt 과 int 함께 사용 시 주의사항...
     * 우리는 이 문자 '1'을 숫자 1로 바꾸고 싶습니다. 그럼 어떻게 해야 할까요?
     * 이 문자 '1'은 char형으로 int형으로 형변환을 하면 아스키코드 값으로 변환이 됩니다.
     * 즉 str.charAt(0); 은 '1'이 출력되고 이걸 int형으로 변환시키면 아스키코드값인 49가 나오게 됩니다.
     * 근데 우리는 1이 필요합니다. 49에서 1이 되기 위해서는 48을 빼줘야 합니다.
     * 네. 그래서 - '0'을 해주는 것 입니다. '0'은 아스키코드 48 입니다.
     * 출처: https://cokes.tistory.com/80 [Cokes Blog:티스토리]
     */
    @Test
    @DisplayName("자연수 뒤집어 배열로 만들기")
    void numberToArray() {
        // char -> int 형변환 (int) / Character.getNumericValue();
        long n = 12345;
        String sn = Long.toString(n);
        int[] answer = new int[sn.length()]; // int[] answer = new int[5];
        int j = 0;
        for (int i = sn.length() - 1; i >= 0; i--) {
            char k = sn.charAt(i);
            System.out.println((int) k);
            answer[j++] = (int) k - 48; //charAt(문자열 숫자)를 int 로 형변환 하면 아스키코드로 형변환 된다.. -48 꼭 해줄 것
        }

        for (int h = 0; h < answer.length; h++) {
            System.out.println("answer[h] = " + answer[h]);
        }
        assertThat("1").isEqualTo("1");

    }

    /**
     * 양의 정수 x가 하샤드 수이려면 x의 자릿수의 합으로 x가 나누어져야 합니다.
     * 예를 들어 18의 자릿수 합은 1+8=9이고, 18은 9로 나누어 떨어지므로 18은 하샤드 수입니다.
     * 자연수 x를 입력받아 x가 하샤드 수인지 아닌지 검사하는 함수, solution을 완성해주세요.
     */
    @Test
    @DisplayName("하샤드 수")
    void haShade() {
        int x = 12;
        String sx = String.valueOf(x);
        //String[] temp = String.valueOf(x).split(""); //빈공간없이 쪼개서 배열로 넣을 수 있음
        int sumNum = 0;
        for (int i = 0; i < sx.length(); i++) {
            sumNum += (int) sx.charAt(i) - 48;
        }
        boolean answer = x % sumNum == 0 ? true : false;
        assertThat(answer).isTrue();
    }

    @Test
    @DisplayName("정수 내림차순으로 배치하기")
    void intSort() {
        //런타임 에러..
        long n = 118372;
        String str = String.valueOf(n);
        String[] aryStr = str.split("");
        ArrayList<String> ary = new ArrayList<String>(Arrays.asList(aryStr)); // String 배열에서 ArrayList 변환
        //Collections.sort(ary); //오름차순
        Collections.sort(ary, Collections.reverseOrder()); //내림차순
        String strAnswer = "";
        for (String i : ary) {
            strAnswer += i;
        }
        long answer = Integer.parseInt(strAnswer);
        assertThat(answer).isEqualTo(873211);
    }

    @Test
    @DisplayName("정수 내림차순으로 배치하기")
    void intSort2() {
        long n = 118372;
        String str = String.valueOf(n);
        String[] aryStr = str.split("");
        Arrays.sort(aryStr); //오름차순
        StringBuilder sb = new StringBuilder();
        for (String aList : aryStr) {
            System.out.println("aList = " + aList);
            sb.append(aList);
            System.out.println("sb = " + sb);
        }
        long answer = Long.parseLong(String.valueOf(sb.reverse())); // reverse 뒤집기
        assertThat(answer).isEqualTo(873211);
    }

    @Test
    @DisplayName("나머지가 1이 되는 수 찾기")
    void remainOneFind() {
//        int n = 10;
        int n = 12;
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 1) {
                answer = i;
                break;
            }
        }
        assertThat(answer).isEqualTo(11);
    }

    /**
     * 1937년 Collatz란 사람에 의해 제기된 이 추측은, 주어진 수가 1이 될 때까지 다음 작업을 반복하면, 모든 수를 1로 만들 수 있다는 추측입니다. 작업은 다음과 같습니다.
     * <p>
     * 1-1. 입력된 수가 짝수라면 2로 나눕니다.
     * 1-2. 입력된 수가 홀수라면 3을 곱하고 1을 더합니다.
     * 2. 결과로 나온 수에 같은 작업을 1이 될 때까지 반복합니다.
     * 예를 들어, 주어진 수가 6이라면 6 → 3 → 10 → 5 → 16 → 8 → 4 → 2 → 1 이 되어 총 8번 만에 1이 됩니다. 위 작업을 몇 번이나 반복해야 하는지 반환하는 함수,
     * solution을 완성해 주세요. 단, 주어진 수가 1인 경우에는 0을, 작업을 500번 반복할 때까지 1이 되지 않는다면 –1을 반환해 주세요.
     */
    @Test
    @DisplayName("콜라츠 추측")
    void Collatz() {
        int num = 626331;
        int answer = 0;
        int cnt = 0;
        if (num == 1) {
            answer = 0;
        } else {
            while (num != 1) {
                cnt++;
                if (cnt <= 500) {
                    if (num % 2 == 0) {
                        num = num / 2;
                    } else if (num % 2 == 1) { //else if 안해주면 안된다? => int 형을 담을 수 없는 오버플러우가 생긴다. 음수로 전향해서 이상한 값이 들어가게됨?
                        num = (num * 3) + 1;
                    }
                    System.out.println("num = " + num);
                    if (num == 1) {
                        answer = cnt;
                        break;
                    }
                } else {
                    answer = -1;
                    break;
                }
            }
        }
        System.out.println("cnt = " + cnt);
        assertThat(answer).isEqualTo(-1);
    }

    @Test
    @DisplayName("핸드폰 번호 가리기")
    void hideOnPhoneNumber() {
        String phoneNumber = "01033334444";
        String answer = "";
        int idx = phoneNumber.length() - 4;
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (i < idx) {
                answer += "*";
            } else if (i >= idx) {
                answer += String.valueOf(phoneNumber.charAt(i));
            }
        }

        assertThat(answer).isEqualTo("*******4444");
    }

    @Test
    @DisplayName("제일 작은 수 제거하기(틀림")
    void minNumRemoveFail() {
        //틀렸음 다시 처음부터 하셈
        //정렬문제가 아님..그냥 배열 선언된 위치에서 가장 작은 수만 빼줘야하는 것임..
        //최소값 구하는 알고리즘 돌려서 원본 배열 중 가장 작은 수 index 값 가지고 그 index 를 제외하고 새로운 배열에다가 넣어줄 것
        int[] arr = {8, 4, 2, 5, 1};
        int[] answer = new int[arr.length - 1];

        if (arr.length <= 1) {
            // answer[0] = -1; //이렇게하면 컴파일 오류남 -1이 확실한 지문이라면
            answer = new int[]{-1}; //이 코드로 지정할 것
        } else {
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < arr.length; i++) {
                list.add(arr[i]);
            }
            Collections.sort(list, Collections.reverseOrder());
            list.remove(list.size() - 1);
            for (int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i);
                System.out.println("answer[i] = " + answer[i]);
            }
        }
        //배열로 하다가 막히거나 안되면 바로 arrayList 생각하자
    }

    @Test
    @DisplayName("제일 작은 수 제거하기")
    void minNumRemoveSuc() {
        //최소값 구하는 알고리즘 돌려서 원본 배열 중 가장 작은 수 index 값 가지고 그 index 를 제외하고 새로운 배열에다가 넣어줄 것
        int[] arr = {8, 4, 2, 5, 1};
        int[] answer = new int[arr.length - 1];
        int min = Integer.MAX_VALUE;
        if (arr.length <= 1) {
            // answer[0] = -1; //이렇게하면 컴파일 오류남 -1이 확실한 지문이라면
            answer = new int[]{-1}; //이 코드로 지정할 것
        } else {
            for (int i : arr) {
                if (min > i) {
                    min = i;
                }
            }
            int idx = 0;
            for (int a : arr) {
                if (min != a) {
                    System.out.println("a = " + a);
                    answer[idx++] = a;
                }
            }
        }
    }

    @Test
    @DisplayName("문자열 내림차순으로 배치하기")
    void stringSortReverse() {
        String s = "Zbcdefg";
        String answer = "";
        char[] chr = s.toCharArray();
        ArrayList<Character> listChr = new ArrayList<Character>();
        for (char a : chr) {
            listChr.add(a);
        }
        Collections.sort(listChr, Collections.reverseOrder());
        for (char a : listChr) {
            answer += a;
        }
        System.out.println("answer = " + answer);
        /**
         *     [다른사람풀이 StringBuilder]
         *     char[] sol = str.toCharArray();
         *     Arrays.sort(sol);
         *     return new StringBuilder(new String(sol)).reverse().toString();
         *
         *     [다른사람풀이2 StringBuffer]
         *     char[] ch = str.toCharArray();
         *     Arrays.sort(ch);
         *     StringBuffer st = new StringBuffer(String.valueOf(ch));
         *     st.reverse();
         *
         *     하나의 작업만 처리하는 클래스라면 StringBuffer 보단 StringBuilder 가 더 빠릅니다
         */
    }

    @Test
    @DisplayName("행렬의 덧셈")
    void arrAdd() {
        int[][] arr1 = {{1, 2}, {2, 3}};
        int[][] arr2 = {{3, 4}, {5, 6}};
//        int[][] arr1 = {{1}, {2}};
//        int[][] arr2 = {{3}, {4}};
        int[][] answer = new int[arr1.length][arr2[0].length];

        System.out.println("arr1.length = " + arr1[0].length);
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2[0].length; j++) {
                //System.out.println("arr1[i][j] = " + arr1[i][j]);
                answer[i][j] = arr1[i][j] + arr2[i][j];
                System.out.println("answer[i][j] = " + answer[i][j]);
            }
        }
    }

    @Test
    @DisplayName("직사각형 별찍기")
    void starSquare() {
        int a = 5;
        int b = 3;
        String star = "";

        for (int i = 0; i < b; i++) {
            for (int j = 0; j < a; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    @Test
    @DisplayName("3진법 뒤집기")
    void threeChange() {
//        String binaryString = Integer.toBinaryString(i); //2수
//        String octalString = Integer.toOctalString(i);   //8진수
//        String hexString = Integer.toHexString(i);       //16진수

//        int binaryToDecimal = Integer.parseInt(binaryString, 2); // 2진수 -> 10진수
//        int binaryToOctal = Integer.parseInt(octalString, 8); // 8진수 -> 10진수
//        int binaryToHex = Integer.parseInt(hexString, 16); // 16진수 -> 10진수
        int n = 125;
        String strN = Integer.toString(n, 3);
        StringBuilder sb = new StringBuilder(strN);
        strN = sb.reverse().toString();
        int answer = 0;
        answer = Integer.parseInt(strN, 3);
        System.out.println("answer = " + answer);
    }

    @Test
    @DisplayName("최대공약수와 최소공배수")
    void gcd() {
        int n = 5;
        int m = 7;

        int max = Math.max(n, m);
        int min = Math.min(n, m);

        //최대 공약수
        //유클리드 호재법 알고리즘 => 최대공약수 구할 때 사용
        //2개의 자연수 a,b 에 대해서 a를 b로 나눈 나머지를 r 이라하면 (단,a>b) a와 b의 최대공약수는 b와 r의 최대공약수와 같다..
        while (min != 0) {
            int r = max % min;
            max = min;
            min = r;
        }

        //최소 공배수 = 두수의 곱 / 최대공약수
        int gcd = n * m / max;

        System.out.println("max = " + max);
        System.out.println("gcd = " + gcd);

        int[] answer = {max, gcd};
        /**
         *     [재귀함수 사용하여 해결한 코드]
         *     public int[] gcdlcm(int a, int b) {
         *         int[] answer = new int[2];
         *
         *           answer[0] = gcd(a,b);
         *         answer[1] = (a*b)/answer[0];
         *         return answer;
         *     }
         *
         *    public static int gcd(int p, int q)
         *    {
         *     if (q == 0) return p;
         *     return gcd(q, p%q);
         *    }
         */
    }

    @Test
    @DisplayName("시저 암호")
    void 시저암호() {
        //알파벳 아스키 26를 나머지 값???
        //전혀 풀지 못하고 이해도 못함
        String s = "a B z";
        int n = 4;
        String answer = "";
        //아스키코드 48
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLowerCase(ch)) {
                System.out.println("ch  = " + (char) ((ch - 'a' + n) % 26 + 'a'));
                ch = (char) ((ch - 'a' + n) % 26 + 'a');
            } else if (Character.isUpperCase(ch)) {
                ch = (char) ((ch - 'A' + n) % 26 + 'A');
            }
            answer += ch;
        }
        System.out.println("answer = " + answer);
    }

    /**
     * 아래 표는 4가지 명함의 가로 길이와 세로 길이를 나타냅니다.
     * <p>
     * 명함 번호	가로 길이	세로 길이
     * 1	60	50
     * 2	30	70
     * 3	60	30
     * 4	80	40
     * 가장 긴 가로 길이와 세로 길이가 각각 80, 70이기 때문에 80(가로) x 70(세로) 크기의 지갑을 만들면 모든 명함들을 수납할 수 있습니다.
     * 하지만 2번 명함을 가로로 눕혀 수납한다면 80(가로) x 50(세로) 크기의 지갑으로 모든 명함들을 수납할 수 있습니다. 이때의 지갑 크기는 4000(=80 x 50)입니다.
     * <p>
     * 모든 명함의 가로 길이와 세로 길이를 나타내는 2차원 배열 sizes가 매개변수로 주어집니다. 모든 명함을 수납할 수 있는 가장 작은 지갑을 만들 때,
     * 지갑의 크기를 return 하도록 solution 함수를 완성해주세요.
     * [문제 해결법]
     * 어렵게 생각할 거 없이 주어지는 명함마다 큰 수와 작은 수를 분류해서, 큰 수는 큰 수끼리 작은 수는 작은 수끼리 비교한 것 중 가장 큰 값을 각각 뽑은뒤 곱해주면 된다!
     */
    @Test
    @DisplayName("최소 직사각형")
    void minSq() {
        //문제 이해가기 어려움
        int[][] size = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        int answer = 0;
        int max_a = 0;
        int min_a = 0;

        for (int i = 0; i < size.length; i++) {
            int mav = Math.max(size[i][0], size[i][1]);
            int miv = Math.min(size[i][0], size[i][1]);
            max_a = Math.max(max_a, mav);
            min_a = Math.max(min_a, miv);
        }
        System.out.println("max_a = " + max_a);
        System.out.println("min_a = " + min_a);

        answer = max_a * min_a;
        System.out.println("answer = " + answer);
    }

    @Test
    @DisplayName("문자열 내 마음대로 정렬하기")
    void stringMyMindSort() {
        //어려움 생각 다시 해봐야할 듯
        //2시간동안 못풀다가 구글링에 찾아서 품 -> 생각차이...
        int n = 1;
        //String[] strings = {"sun", "bed", "car"};
        String[] strings = {"abce", "abcd", "cdx"};
        String[] answer = new String[strings.length];
        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < strings.length; i++) {
            list.add(strings[i].charAt(n) + strings[i]);
        }

        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).substring(1, list.get(i).length());
        }

        for (String str : answer) {
            System.out.println("str = " + str);
        }
    }

    @Test
    @DisplayName("숫자 문자열과 영단어")
    void numberStrEnglish() {
        String s = "one4seveneight";
        //String s = "23four5six7";
        int answer = 0;

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);

        System.out.println("map.keySet() = " + map.keySet());

        for (String key : map.keySet()) {
            if (s.contains(key)) {
                System.out.println("key = " + key);
                s = s.replace(key, String.valueOf(map.get(key)));
            }
            ;
        }
        answer = Integer.parseInt(s);

        System.out.println("s = " + s);
        System.out.println("answer = " + answer);
    }

    @Test
    @DisplayName("소수 찾기")
    void sosufind() {
        //효율성에서 탈락
        //break 가 있어야되는 거 같음
        //에라토스테네스의 체 ?????
        int n = 10;
        int answer = 0;
        boolean bol = false;

        for (int i = 2; i <= n; i++) {
            bol = sosu(i);
            if (!bol) {
                answer++;
            }
        }
        System.out.println("answer = " + answer);

//        System.out.println("Math.sqrt(4) = " + Math.sqrt(4));
        // 구글링 해서 나온 답
        answer = 0;
        for (int i = 2; i <= n; i++) {
            boolean flag = true;
            for (int j = 2; j <= Math.sqrt(i); j++) { //에라토스테네서의 체????
                if (i % j == 0) { //나누어떨어지면 소수X
                    flag = false; //falg를 false로 바꿔서 소수가 아니라고 체크
                    break; //break를 안해주면 효율성 테스트 통과 못함
                }
            }
            if (flag == true) //소수인 숫자는 flag가 변하지 않고 true
                answer++;
        }

        System.out.println("answer = " + answer);


    }

    public boolean sosu(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return true;
            }
        }
        return false;
    }

    @Test
    @DisplayName("삼총사")
    void threeFriend() {
        int[] number = {-3, -2, -1, 0, 1, 2, 3};
        int answer = 0;

        for (int i = 0; i < number.length - 2; i++) { //for 문 시작지점과 끝지점 확인 꼭 해라
            for (int j = i + 1; j < number.length - 1; j++) { //for 문 시작지점과 끝지점 확인 꼭 해라
                for (int k = j + 1; k < number.length; k++) { //for 문 시작지점과 끝지점 확인 꼭 해라
                    if (number[i] + number[j] + number[k] == 0) {
                        System.out.println("[i]: " + i + " number[i] = " + number[i] + " [j]: " + j + " number[j] = " + number[j] + " [k]: " + k + " number[k] = " + number[k]);
                        answer++;
                    }
                }
            }
        }

        System.out.println("answer = " + answer);
    }

    @Test
    @DisplayName("다트 게임 - 카카오")
    void dartGame() {
        String dartResult = "1S2D*3T";
        //String dartResult = "1D2S#10S";
        int answer = 0;

        String num = "";
        int cur = 0;
        int idx = 0;
        int dart[] = new int[3];
        for (int i = 0; i < dartResult.length(); i++) {
            char ch = dartResult.charAt(i);
            if (ch >= '0' && ch <= '9') {
                num += String.valueOf(ch);
                System.out.println("num = " + num);
            } else if (ch == 'S' || ch == 'D' || ch == 'T') {
                cur = Integer.parseInt(num);
                if (ch == 'S') {
                    cur = (int) Math.pow(cur, 1);
                } else if (ch == 'D') {
                    cur = (int) Math.pow(cur, 2);
                } else if (ch == 'T') {
                    cur = (int) Math.pow(cur, 3);
                }
                dart[idx++] = cur;
                num = "";
                System.out.println("cur = " + cur);
            } else if (ch == '*' || ch == '#') {
                if (ch == '#') {
                    dart[idx - 1] *= -1;
                } else if (ch == '*') {
                    dart[idx - 1] *= 2;
                    if (idx - 2 >= 0) {
                        dart[idx - 2] *= 2;
                    }
                }
            }
        }

        for (int i = 0; i < dart.length; i++) {
            answer += dart[i];
        }
        System.out.println("answer = " + answer);
    }

    /**
     * [1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5]	"right"	"LRLLLRLLRRL"
     * [7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2]	"left"	"LRLLRRLLLRR"
     * [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]	    "right"	"LLRLLRLLRL"
     */
    @Test
    @DisplayName("키패드 누르기 - 카카오")
    void keyPad() {
//        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
//        String hand = "right";
        String answer = "";
        // int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        // String hand = "right";
        int[] numbers = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        String hand = "left";

        //키패드에 숫자를 대입한다. 1~9 , * : 10,  0 : 11, # : 12

        int leftLocation = 10;
        int rightLocation = 12;

        for (int num : numbers) {
            if (num == 1 || num == 4 || num == 7) {
                answer += "L";
                leftLocation = num;
            } else if (num == 3 || num == 6 || num == 9) {
                answer += "R";
                rightLocation = num;
            } else {
                if (num == 0) num = 11;
                // 아래 식이 나오는 방법을 모르겠음
                // 키패드 위아래로 3차이, 양옆으로 1차이인 것을 이용해서 아래와 같이 거리를 구하는 식을 이용해서 풀었다.
                int leftdist = Math.abs(num - leftLocation) / 3 + Math.abs(num - leftLocation) % 3;
                int rightdist = Math.abs(num - rightLocation) / 3 + Math.abs(num - rightLocation) % 3;

                System.out.println("num = " + num);
                System.out.println("leftLocation = " + leftLocation);

                System.out.println("Math.abs(num - leftLocation / 3 = " + Math.abs(num - leftLocation) / 3);
                System.out.println("Math.abs(num - leftLocation) % 3 = " + Math.abs(num - leftLocation) % 3);

                System.out.println("leftdist = " + leftdist);
                System.out.println("rightdist = " + rightdist);

                if (leftdist < rightdist) {
                    answer += "L";
                    leftLocation = num;
                } else if (leftdist > rightdist) {
                    answer += "R";
                    rightLocation = num;
                } else if (leftdist == rightdist) {
                    if (hand == "left") {
                        answer += "L";
                        leftLocation = num;
                    } else if (hand == "right") {
                        answer += "R";
                        rightLocation = num;
                    }
                }
            }
        }
        System.out.println("answer = " + answer);
    }

    /**
     * Stack<Integer> stack = new Stack<>();
     * Stack 객체로 푸는 문제임 Stack 관련된 함수들 확인할 것
     * public Element push(Element item); // 데이터 추가
     * public Element pop(); // 최근에 추가된(Top) 데이터 삭제
     * public Element peek(); // 최근에 추가된(Top) 데이터 조회
     * public boolean empty(); // stack의 값이 비었는지 확인, 비었으면 true, 아니면 false
     * public int seach(Object o); // 인자값으로 받은 데이터의 위치 반환, 그림으로 설명하겠음
     * <p>
     * [정답 코드]
     * Stack<Integer> stack = new Stack<>();
     * stack.push(0);
     * <p>
     * for (int move : moves) {
     * for (int j = 0; j < board.length; j++) {
     * if (board[j][move - 1] != 0) {
     * if (stack.peek() == board[j][move - 1]) {
     * stack.pop();
     * answer += 2;
     * } else {
     * stack.push(board[j][move - 1]);
     * }
     * board[j][move - 1] = 0;
     * break;
     * }
     * }
     * }
     * return answer;
     */
    @Test
    @DisplayName("크레인 인형뽑기 게임")
    void cranesGame() {
        int[][] board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};


        //각 배열을 하나씩 뽑아서 새로운 배열에 넣어줘야할듯?

        //index 가 1 일 때 4 ->
        //00 10 20 30 40
        //01 11 21 31 41

        //array List HashMap 으로? 자리 - 인형종류
        //arrayList 0 번째에
        ArrayList<HashMap<Integer, Integer>> stackList = new ArrayList<>();
        HashMap<Integer, Integer> stackMap = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            stackMap = new HashMap<>();
            for (int j = 0; j < board.length; j++) {
                int stack = board[i][j];
                stackMap.put(j, stack);
            }
            stackList.add(stackMap);
        }

        for (HashMap map : stackList) {
            System.out.println("map = " + map);
        }
        //int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        HashMap<Integer, Integer> selectMap = new HashMap<>();
        for (int k = 0; k < moves.length; k++) {
//            int w = board.length -1; // 꺼낼떄마다 마지막 index 로 초기화
            int w = 0;
            System.out.println("w = " + w);
            System.out.println("moves[k] = " + moves[k]);
            while (w <= board.length - 1) {
                if (stackList.get(w).get(moves[k] - 1) != 0) {
                    // 인형을 뽑았으면 그 자리에 value 값은 0으로 변경해야함
                    System.out.println("stackList.get(w).get(moves[k]) - 1  = " + stackList.get(w).get(moves[k] - 1));
                    stackList.get(w).put(moves[k] - 1, stackList.get(w).get(moves[k] - 1) - stackList.get(w).get(moves[k] - 1));
                    // 뽑은 인형을 result 에 넣어두고 다음에 들어오는 인형값과 같을 경우 count ++; 하고 지워줘야함?
                    // 뽑기까지는 했는데 count 세는걸 구현 못함 -> 구현 하더라도 런타임 오류 발생 stack으로 풀어야함..
                    break;
                } else if ((stackList.get(w).get(moves[k] - 1) == 0)) {
                    // 해당 인덱스에 인형이 없으면 그 다음 인형을 찾아야함
                    w += +1;
                }
            }
        }

        for (HashMap map : stackList) {
            System.out.println("map 222 = " + map);
        }

    }

    @Test
    @DisplayName("크레인 인형뽑기 게임")
    void cranesGameStack() {
        int[][] board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};

        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (int move : moves) {
            for (int i = 0; i < board.length; i++) {
                if (board[i][move - 1] != 0) {
                    //인형이 존재할 경우
                    //peek 최근에 들어온 데이터 조회
                    if (stack.peek() == board[i][move - 1]) { // 양 옆이 서로 같다면
                        stack.pop(); //최근에 들어온 데이터 삭제
                        answer += 2;
                    } else {
                        // 양 옆이 서로 같지 않다면 push
                        stack.push(board[i][move - 1]);
                    }

                    // 0 으로 변경해줘야 다음에 또 안 뽑히니깐 변경해줘야함..
                    board[i][move - 1] = 0;
                    break;
                }
            }
        }
    }

    @Test
    @DisplayName("신규 아이디 추천")
    /**
     * 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
     * 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
     * 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
     * 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
     * 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
     * 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
     *      만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
     * 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
     */
    void newIdRecommend() {
        //String new_id = "...!@BaT#*..y.abcdefghijklm.";
        String new_id = "m";
        //String answer = "bat.y.abcdefghi";
        String answer = "";
        // 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
        answer = new_id.toLowerCase();
        System.out.println("new_id Case 1 = " + answer);
        // 2단계 new_id 에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
        answer = answer.replaceAll("[^-_.a-z0-9]", ""); // 정규식 모르면 못품..
        System.out.println("new_id Case 2 = " + answer);
        // 3단계 new_id 에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
        answer = answer.replaceAll("\\.+", ".");
        System.out.println("new_id Case 3 = " + answer);
        // 4단계 new_id 에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
        answer = answer.replaceAll("^[.]|[.]$", "");
        System.out.println("new_id Case 4 = " + answer);
        // 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
        if (answer.length() == 0) answer = "a";
        // 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다. 만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
        if (answer.length() >= 16) {
            answer = answer.substring(0, 15);
            answer = answer.replaceAll("[.]$", "");
        }
        // 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
        if (answer.length() <= 2) {
            answer = caseSeven(answer);
        }
        System.out.println("new_id Case 7 = " + answer);
    }

    public String caseSeven(String str) {
        while (str.length() <= 2) {
            str += str.substring(str.length() - 1);
            caseSeven(str);
        }
        return str;
    }
}

