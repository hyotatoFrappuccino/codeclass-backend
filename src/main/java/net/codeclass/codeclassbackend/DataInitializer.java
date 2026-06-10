package net.codeclass.codeclassbackend;

import lombok.RequiredArgsConstructor;
import net.codeclass.codeclassbackend.entity.*;
import net.codeclass.codeclassbackend.repository.*;
import net.codeclass.codeclassbackend.repository.CommentRepository;
import net.codeclass.codeclassbackend.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProblemRepository problemRepository;
    private final JudgeResultRepository judgeResultRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public void run(String... args) {
        initProblems();
        initJudgeResults();
        initPosts();
    }

    private void initProblems() {
        List<Problem> problems = List.of(
            p(1,  "A+B",                  98234, 112400, "87%", "두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.", "첫째 줄에 A와 B가 주어진다. (0 < A, B < 10)", "첫째 줄에 A+B를 출력한다.", "1 2", "3", "하", "수학/구현"),
            p(2,  "A-B",                  76543,  98100, "78%", "두 정수 A와 B를 입력받은 다음, A-B를 출력하는 프로그램을 작성하시오.", "첫째 줄에 A와 B가 주어진다. (0 < A, B < 10)", "첫째 줄에 A-B를 출력한다.", "3 2", "1", "하", "수학/구현"),
            p(3,  "피보나치 함수",         54321,  89500, "61%", "피보나치 수를 구하는 프로그램을 작성하시오.", "첫째 줄에 테스트 케이스의 수 T가 주어진다. 각 테스트 케이스는 정수 N이 주어진다. (0 ≤ N ≤ 40)", "각 테스트 케이스마다 F(N)을 출력한다.", "3\n0\n1\n3", "0\n1\n2", "하", "다이나믹 프로그래밍/재귀"),
            p(4,  "유기농 배추",           42100,  67800, "62%", "배추밭에서 서로 인접한 배추들의 연결된 그룹 수를 구하시오.", "테스트 케이스 수 T, 각 케이스마다 M N K와 배추 위치가 주어진다.", "각 테스트 케이스마다 필요한 배추흰지렁이 수를 출력한다.", "2\n5 3 6\n0 2\n1 2\n2 2\n3 2\n4 2\n4 0\n8 8 1\n0 0", "2\n1", "중", "BFS/DFS"),
            p(5,  "수열 정렬",            31200,  54300, "57%", "N개의 수로 이루어진 수열을 오름차순으로 정렬하는 프로그램을 작성하시오.", "첫째 줄에 N이 주어지고, 둘째 줄에 N개의 수가 주어진다. (1 ≤ N ≤ 1,000,000)", "정렬된 수열을 출력한다.", "5\n5 4 3 2 1", "1 2 3 4 5", "하", "정렬"),
            p(6,  "소수 쌍",              18700,  41200, "45%", "두 소수의 합으로 나타낼 수 있는 짝수의 개수를 구하시오.", "첫째 줄에 짝수 N이 주어진다. (4 ≤ N ≤ 1,000,000)", "N을 두 소수의 합으로 나타내는 경우의 수를 출력한다.", "10", "2", "중", "수학/에라토스테네스의 체"),
            p(7,  "강원대학교 축제",        9800,  28900, "34%", "강원대학교 축제에서 N개의 부스 중 최대 이익을 구하는 프로그램을 작성하시오.", "첫째 줄에 부스 수 N, 둘째 줄에 각 부스의 이익이 주어진다.", "최대 이익을 출력한다.", "4\n1 -3 2 4", "6", "중", "다이나믹 프로그래밍"),
            p(8,  "최단 경로",            23400,  52100, "45%", "방향 그래프에서 1번 노드로부터 나머지 노드까지의 최단 경로를 구하시오.", "첫째 줄에 노드 수 V와 간선 수 E, 둘째 줄에 시작 노드, 이후 간선 정보가 주어진다.", "각 노드까지의 최단 거리를 출력하고, 도달 불가능하면 INF를 출력한다.", "5 6\n1\n5 1 1\n1 2 2\n1 3 3\n2 3 4\n2 4 5\n3 4 6", "0\n2\n3\n7\nINF", "중", "다익스트라"),
            p(9,  "다리를 지나는 트럭",    37800,  61200, "62%", "트럭들이 다리를 건너는 데 걸리는 최소 시간을 구하시오.", "다리 길이 bridge_length, 최대 하중 weight, 트럭 무게 목록이 주어진다.", "모든 트럭이 다리를 건너는 데 걸리는 최솟값을 return한다.", "2 10\n[7, 4, 5, 6]", "8", "중", "시뮬레이션/큐"),
            p(10, "문자열 압축",          29100,  44700, "65%", "문자열을 1개 이상의 단위로 잘라서 압축했을 때 가장 짧은 길이를 구하시오.", "압축할 문자열 s가 주어진다. (1 ≤ len(s) ≤ 1,000)", "압축된 문자열의 최솟값을 return한다.", "aabbaccc", "7", "중", "구현/문자열"),
            p(11, "괄호 변환",           15300,  31800, "48%", "잘못된 괄호 문자열을 올바른 괄호 문자열로 변환하는 알고리즘을 작성하시오.", "괄호 문자열 p가 주어진다.", "올바른 괄호 문자열을 return한다.", "(())()", "(())()", "중", "재귀/스택"),
            p(12, "자물쇠와 열쇠",        12600,  34500, "37%", "열쇠를 이용해 자물쇠를 열 수 있는지 확인하는 프로그램을 작성하시오.", "열쇠 배열 key, 자물쇠 배열 lock이 주어진다.", "열쇠로 자물쇠를 열 수 있으면 true, 아니면 false를 return한다.", "[[0,0,0],[1,0,0],[0,1,1]]\n[[1,1,1],[1,1,0],[1,0,1]]", "true", "상", "구현/시뮬레이션"),
            p(13, "외벽 점검",            6700,  27300, "25%", "최소 인원으로 취약 지점을 모두 점검할 수 있는 수를 구하시오.", "원형 벽 길이 n, 취약 지점 목록 weak, 각 친구의 이동 거리 dist가 주어진다.", "취약 지점을 모두 점검하기 위한 최소 인원 수를 return한다.", "12\n[1,5,6,10]\n[1,2,3,4]", "2", "상", "브루트포스/순열"),
            p(14, "블록 이동하기",         5400,  24100, "22%", "로봇이 (1,1)에서 (N,N)으로 이동하는 최소 시간을 구하시오.", "N×N 크기의 격자 board가 주어진다.", "최소 이동 시간을 return한다.", "[[0,0,0,1,1],[0,0,0,1,0],[0,1,0,1,1],[1,1,0,0,1],[0,0,0,0,0]]", "7", "상", "BFS"),
            p(15, "기둥과 보 설치",        8900,  29800, "30%", "기둥과 보를 설치하고 삭제하는 명령을 수행한 결과를 구하시오.", "격자 크기 n, 명령어 목록 build_frame이 주어진다.", "설치된 구조물의 좌표를 return한다.", "5\n[[1,0,0,1],[1,1,1,1],[2,1,0,1]]", "[[1,0,0],[1,1,1],[2,1,0]]", "상", "구현/시뮬레이션"),
            p(16, "N-Queen",             43200,  78500, "55%", "N×N 체스판에 N개의 퀸을 서로 공격할 수 없게 놓는 경우의 수를 구하시오.", "첫째 줄에 N이 주어진다. (1 ≤ N ≤ 15)", "N-Queen 문제의 해의 수를 출력한다.", "8", "92", "중", "백트래킹"),
            p(17, "숫자 야구",           56700,  82300, "69%", "질문과 결과를 이용해 가능한 답의 수를 구하는 프로그램을 작성하시오.", "질문의 수 N과 각 질문 및 스트라이크/볼 수가 주어진다.", "가능한 답의 수를 return한다.", "2\n123 1 1\n356 1 0", "2", "중", "브루트포스"),
            p(18, "카드 짝 맞추기",      11200,  31400, "36%", "카드를 모두 제거하는 데 필요한 최소 클릭 횟수를 구하시오.", "4×4 격자 board와 커서 위치 r, c가 주어진다.", "최소 클릭 횟수를 return한다.", "[[1,0,0,3],[2,0,0,0],[0,0,0,2],[3,0,1,0]]\n1\n0", "14", "상", "BFS/백트래킹"),
            p(19, "마법사 상어와 파이어볼",  7300,  26500, "28%", "K번의 이동 후 남아있는 파이어볼의 질량 합을 구하시오.", "N, M, K와 파이어볼 정보가 주어진다.", "K번 이동 후 남아있는 파이어볼의 질량 합을 출력한다.", "4 2 2\n1 1 3 4 1\n2 2 3 2 7", "9", "상", "시뮬레이션"),
            p(20, "인구 이동",           14800,  39700, "37%", "인구 이동이 며칠 동안 발생하는지 구하시오.", "N, L, R과 각 나라의 인구가 주어진다.", "인구 이동이 며칠 동안 발생하는지 출력한다.", "2 20 50\n50 30\n20 40", "1", "중", "BFS/시뮬레이션"),
            p(21, "연구소",             22300,  47600, "47%", "바이러스가 퍼질 수 없는 안전한 영역의 최댓값을 구하시오.", "N, M과 연구소 지도가 주어진다. (3 ≤ N, M ≤ 8)", "안전 영역 크기의 최댓값을 출력한다.", "7 7\n2 0 0 0 1 1 0\n0 0 1 0 1 2 0\n0 1 1 0 1 0 0\n0 1 0 0 0 0 0\n0 0 0 0 0 1 1\n0 1 0 0 0 0 0\n0 1 0 0 0 0 0", "27", "중", "BFS/브루트포스"),
            p(22, "주사위 굴리기",       19600,  43200, "45%", "주사위를 굴린 후 이동 방향에 따른 주사위 윗면 숫자를 구하시오.", "N, M, 시작 좌표, 이동 횟수 K, 지도 및 명령이 주어진다.", "주사위를 굴릴 때마다 주사위 윗면에 쓰인 수를 출력한다.", "4 2 0 0 8\n0 2\n3 4\n5 6\n7 8\n4 4 4 1 3 3 2 2", "0\n0\n3\n0\n0\n8\n6\n3", "중", "시뮬레이션"),
            p(23, "미로 탐색",          63400,  91200, "70%", "(1,1)에서 (N,M)으로 이동할 때 지나야 하는 최소 칸 수를 구하시오.", "N과 M, 미로 정보가 주어진다. (2 ≤ N, M ≤ 100)", "최소 칸 수를 출력한다.", "4 6\n101111\n101010\n101011\n111011", "15", "하", "BFS"),
            p(24, "토마토",             71200, 103400, "69%", "모든 토마토가 익을 때까지 걸리는 최소 날짜를 구하시오.", "M, N과 창고 상태가 주어진다. (2 ≤ M, N ≤ 1,000)", "모든 토마토가 익는 최소 날짜를 출력한다.", "6 4\n0 0 0 0 0 0\n0 0 0 0 0 0\n0 0 0 0 0 0\n0 0 0 0 0 1", "8", "중", "BFS"),
            p(25, "단지번호붙이기",      68900,  98700, "70%", "아파트 단지 수와 각 단지에 속하는 집의 수를 구하시오.", "N과 지도 정보가 주어진다. (5 ≤ N ≤ 25)", "총 단지 수를 출력하고, 각 단지 집 수를 오름차순으로 출력한다.", "7\n0110100\n0110101\n1110101\n0000111\n0100000\n0111110\n0111000", "3\n7\n8\n9", "하", "BFS/DFS"),
            p(26, "적록색약",           38700,  62300, "62%", "적록색약인 사람과 아닌 사람이 보는 구역의 수를 각각 구하시오.", "N과 그림 정보가 주어진다. (1 ≤ N ≤ 100)", "일반인과 적록색약인 사람이 보는 구역 수를 출력한다.", "5\nRRRBB\nGGBBB\nBBBBB\nBBRRR\nBBRGG", "4 3", "하", "BFS/DFS"),
            p(27, "나이트의 이동",       45100,  71800, "63%", "체스판 위의 나이트가 이동하는 최소 횟수를 구하시오.", "테스트 케이스 수 T, 각 케이스마다 체스판 크기 l, 시작·도착 좌표가 주어진다.", "각 테스트 케이스마다 최소 이동 횟수를 출력한다.", "3\n8\n0 0\n7 0\n100\n0 0\n30 50\n10\n1 1\n1 1", "5\n28\n0", "하", "BFS"),
            p(28, "빙산",              21400,  48900, "44%", "빙산이 두 덩어리 이상으로 분리되는 최초의 시간을 구하시오.", "N, M과 빙산 지도가 주어진다.", "분리되는 최초 시간을 출력하고, 불가능하면 0을 출력한다.", "5 7\n0 0 0 0 0 0 0\n0 2 4 5 3 0 0\n0 3 0 2 5 2 0\n0 7 6 2 4 0 0\n0 0 0 0 0 0 0", "3", "중", "BFS/시뮬레이션"),
            p(29, "치킨 배달",         34500,  59200, "58%", "도시의 치킨 거리의 최솟값을 구하시오.", "N, M과 도시 지도가 주어진다.", "도시의 치킨 거리의 최솟값을 출력한다.", "5 3\n0 0 1 0 0\n0 0 2 0 1\n0 1 2 0 0\n0 0 1 0 0\n0 0 0 0 2", "5", "중", "브루트포스/조합"),
            p(30, "감시",             16700,  41300, "40%", "CCTV가 감시할 수 없는 영역의 최솟값을 구하시오.", "N, M과 사무실 지도가 주어진다.", "사각지대의 최소 크기를 출력한다.", "4 6\n0 0 0 0 0 0\n0 0 0 0 0 0\n0 0 1 0 6 0\n0 0 0 0 0 0", "20", "중", "브루트포스/시뮬레이션"),
            p(31, "벽 부수고 이동하기", 27800,  56700, "49%", "(1,1)에서 (N,M)으로 이동할 때 벽을 1개 부수는 경우를 포함한 최단 경로를 구하시오.", "N, M과 지도 정보가 주어진다. (1 ≤ N, M ≤ 1,000)", "최단 거리를 출력하고 이동이 불가능하면 -1을 출력한다.", "6 4\n0100\n1110\n1000\n0000\n0111\n0000", "15", "중", "BFS"),
            p(32, "숨바꼭질",          52300,  83100, "63%", "수빈이가 동생을 찾는 가장 빠른 시간을 구하시오.", "수빈이의 위치 N과 동생의 위치 K가 주어진다. (0 ≤ N, K ≤ 100,000)", "수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.", "5 17", "4", "중", "BFS"),
            p(33, "이분 그래프",        29700,  54600, "54%", "그래프가 이분 그래프인지 아닌지 판별하는 프로그램을 작성하시오.", "테스트 케이스 수 K, 각 케이스마다 V, E와 간선 정보가 주어진다.", "이분 그래프이면 YES, 아니면 NO를 출력한다.", "2\n3 2\n1 3\n2 3\n4 4\n1 2\n2 3\n3 4\n4 2", "YES\nNO", "중", "그래프/BFS/DFS"),
            p(34, "플로이드 와샬",      41600,  72900, "57%", "모든 쌍의 최단 경로를 구하는 프로그램을 작성하시오.", "도시 수 N, 버스 수 M과 각 버스의 출발·도착·비용이 주어진다.", "N×N 행렬로 최단 거리를 출력하고, 이동 불가능하면 0을 출력한다.", "4\n7\n1 2 4\n1 3 2\n1 4 6\n2 1 3\n2 3 1\n3 1 2\n4 2 1", "0 4 2 6\n3 0 1 5\n2 6 0 4\n4 1 2 0", "중", "최단 경로"),
            p(35, "벨만-포드",         18900,  43100, "44%", "음수 간선이 포함된 그래프에서 최단 경로를 구하시오.", "V, E와 각 간선의 정보가 주어진다.", "음수 사이클이 있으면 -1, 없으면 최단 거리를 출력한다.", "3 4\n1 2 4\n1 3 3\n2 1 -2\n3 2 -3", "-1", "중", "최단 경로"),
            p(36, "크루스칼 알고리즘",  32400,  61800, "52%", "최소 스패닝 트리의 가중치 합을 구하시오.", "V, E와 각 간선의 정보가 주어진다.", "최소 스패닝 트리의 가중치 합을 출력한다.", "4 5\n1 2 6\n1 3 3\n2 3 2\n2 4 5\n3 4 4", "9", "중", "최소 스패닝 트리"),
            p(37, "프림 알고리즘",     24100,  52300, "46%", "프림 알고리즘으로 최소 스패닝 트리를 구하시오.", "V, E와 각 간선의 정보가 주어진다.", "최소 스패닝 트리의 가중치 합을 출력한다.", "5 7\n1 2 2\n1 3 3\n2 3 1\n2 4 4\n3 4 2\n3 5 5\n4 5 1", "7", "중", "최소 스패닝 트리"),
            p(38, "위상 정렬",         27300,  55700, "49%", "선수 과목이 있는 강의 순서를 위상 정렬로 구하시오.", "과목 수 N, 순서 수 M과 각 순서 쌍이 주어진다.", "위상 정렬 결과를 출력한다.", "4 4\n1 2\n3 2\n3 4\n1 4", "1 3 2 4", "중", "위상 정렬"),
            p(39, "강한 연결 요소",      9100,  31200, "29%", "방향 그래프의 강한 연결 요소를 구하시오.", "V, E와 각 간선 정보가 주어진다.", "SCC의 수와 각 SCC를 출력한다.", "7 9\n1 2\n2 5\n5 1\n3 6\n6 4\n4 3\n2 3\n4 2\n7 4", "3\n1 2 5\n3 4 6\n7", "상", "그래프/SCC"),
            p(40, "세그먼트 트리",      16400,  42700, "38%", "구간 합을 빠르게 구하는 세그먼트 트리를 구현하시오.", "N, M, K와 데이터가 주어진다.", "쿼리에 대한 결과를 출력한다.", "5 2 2\n1 2 3 4 5\n1 3 6\n2 2 5\n1 5 2\n2 3 5", "17\n12", "상", "세그먼트 트리"),
            p(41, "펜윅 트리",         12800,  37500, "34%", "펜윅 트리(BIT)를 이용해 구간 합 쿼리를 처리하시오.", "N, M, K와 데이터 및 쿼리가 주어진다.", "각 쿼리 결과를 출력한다.", "5 2 2\n1 2 3 4 5\n1 3 6\n2 2 5", "20", "상", "자료구조/펜윅 트리"),
            p(42, "이진 탐색",         58900,  87400, "67%", "정렬된 수열에서 특정 값의 위치를 이진 탐색으로 찾으시오.", "N과 수열, 쿼리 수 M과 각 쿼리가 주어진다.", "각 쿼리의 위치를 출력하고, 없으면 -1을 출력한다.", "5\n1 3 5 7 9\n3\n1\n6\n9", "1\n-1\n5", "하", "이진 탐색"),
            p(43, "이분 탐색 응용",    33700,  63900, "53%", "이분 탐색을 응용해 최적의 값을 구하시오.", "N, M과 각 조건 값이 주어진다.", "조건을 만족하는 최솟값을 출력한다.", "4 6\n1 2 3 4", "3", "중", "이진 탐색"),
            p(44, "투 포인터",         44200,  73600, "60%", "합이 M인 연속 구간의 수를 투 포인터로 구하시오.", "N, M과 수열이 주어진다. (1 ≤ N ≤ 10,000,000)", "합이 M인 연속 구간의 수를 출력한다.", "4 2\n1 1 1 1", "3", "중", "투 포인터"),
            p(45, "슬라이딩 윈도우",   26500,  51400, "52%", "크기 L인 슬라이딩 윈도우의 최솟값을 구하시오.", "N, L과 수열이 주어진다.", "각 윈도우의 최솟값을 출력한다.", "8 3\n1 3 -1 -3 5 3 6 7", "-1 -3 -3 -3 3 3", "중", "슬라이딩 윈도우/덱"),
            p(46, "KMP 알고리즘",      11700,  36800, "32%", "문자열에서 패턴이 등장하는 모든 위치를 KMP로 찾으시오.", "텍스트 문자열과 패턴 문자열이 주어진다.", "패턴이 등장하는 횟수와 위치를 출력한다.", "ABCABCABCABC\nABCABC", "3\n1 4 7", "상", "문자열/KMP"),
            p(47, "트라이",             8200,  29400, "28%", "트라이 자료구조를 이용해 문자열 검색을 구현하시오.", "문자열 수 N과 각 문자열, 쿼리 수 M과 각 쿼리가 주어진다.", "각 쿼리에 대해 존재하면 YES, 아니면 NO를 출력한다.", "3\nhello\nworld\nhi\n2\nhello\nbye", "YES\nNO", "상", "자료구조/트라이"),
            p(48, "LCA",              13500,  38200, "35%", "두 노드의 최소 공통 조상(LCA)을 구하시오.", "N과 트리 정보, 쿼리 수 M과 각 쿼리가 주어진다.", "각 쿼리의 LCA를 출력한다.", "7\n1 2\n1 3\n2 4\n2 5\n3 6\n3 7\n2\n6 5\n4 7", "1\n2", "상", "트리/LCA"),
            p(49, "오일러 경로",        7600,  27900, "27%", "모든 간선을 정확히 한 번씩 지나는 오일러 경로를 구하시오.", "V, E와 간선 정보가 주어진다.", "오일러 경로가 존재하면 경로를, 없으면 -1을 출력한다.", "5 6\n1 2\n2 3\n3 1\n1 4\n4 5\n5 1", "2 3 1 4 5 1 2", "상", "그래프/오일러 경로"),
            p(50, "네트워크 플로우",    4900,  22600, "22%", "최대 유량 알고리즘으로 소스에서 싱크까지의 최대 유량을 구하시오.", "V, E와 각 간선의 용량 정보가 주어진다.", "최대 유량을 출력한다.", "6 9\n1 2 12\n1 3 13\n2 4 12\n3 2 5\n3 4 14\n4 6 4\n4 5 7\n5 6 7\n2 6 16", "23", "상", "최대 유량")
        );
        problemRepository.saveAll(problems);
    }

    private void initJudgeResults() {
        LocalDateTime now = LocalDateTime.now();
        List<JudgeResult> results = List.of(
            jr("choi99",      5L, "맞았습니다!", 5376, 54,  "Python3", 240, now.minusHours(1).minusMinutes(10)),
            jr("parkjava",    1L, "틀렸습니다!", 3968, 68,  "C++14",   365, now.minusHours(1).minusMinutes(5)),
            jr("leedev",      4L, "컴파일 에러",    0,  0,  "C#",      720, now.minusHours(1)),
            jr("kimcoding",   2L, "맞았습니다!", 4608, 40,  "Python3", 210, now.minusMinutes(55)),
            jr("hyojae.lee",  3L, "맞았습니다!", 6400, 70,  "C++17",   480, now.minusMinutes(50)),
            jr("choi99",      1L, "맞았습니다!", 5248, 50,  "Python3", 260, now.minusMinutes(48)),
            jr("parkjava",    5L, "시간 초과",      0, 2000,"Java",    850, now.minusMinutes(45)),
            jr("leedev",      2L, "맞았습니다!", 4480, 36,  "Kotlin",  395, now.minusMinutes(42)),
            jr("kimcoding",   4L, "런타임 에러",    0,  0,  "C99",     610, now.minusMinutes(40)),
            jr("hyojae.lee",  1L, "맞았습니다!", 5504, 48,  "Python3", 230, now.minusMinutes(38)),
            jr("choi99",      3L, "틀렸습니다!", 3712, 76,  "C#",      490, now.minusMinutes(35)),
            jr("parkjava",    2L, "맞았습니다!", 4352, 42,  "C++14",   340, now.minusMinutes(33)),
            jr("leedev",      5L, "맞았습니다!", 5888, 60,  "Python3", 275, now.minusMinutes(30)),
            jr("kimcoding",   1L, "컴파일 에러",    0,  0,  "Java",    740, now.minusMinutes(28)),
            jr("hyojae.lee",  4L, "맞았습니다!", 7168, 88,  "C++17",   560, now.minusMinutes(25)),
            jr("choi99",      2L, "맞았습니다!", 4224, 56,  "Python3", 215, now.minusMinutes(22)),
            jr("parkjava",    3L, "틀렸습니다!", 3840, 72,  "Kotlin",  430, now.minusMinutes(20)),
            jr("leedev",      1L, "맞았습니다!", 5120, 44,  "C99",     310, now.minusMinutes(18)),
            jr("kimcoding",   5L, "시간 초과",      0, 2000,"Java",    920, now.minusMinutes(15)),
            jr("hyojae.lee",  2L, "맞았습니다!", 4512, 38,  "Python3", 198, now.minusMinutes(12)),
            jr("choi99",      4L, "런타임 에러",    0,  0,  "C#",      670, now.minusMinutes(10)),
            jr("parkjava",    1L, "맞았습니다!", 6200, 64,  "C++14",   380, now.minusMinutes(8)),
            jr("leedev",      3L, "컴파일 에러",    0,  0,  "Java",    800, now.minusMinutes(5)),
            jr("kimcoding",   2L, "틀렸습니다!", 4096, 80,  "C++17",   512, now.minusMinutes(2)),
            jr("hyojae.lee",  1L, "맞았습니다!", 5732, 52,  "Python3", 244, now.minusMinutes(1))
        );
        judgeResultRepository.saveAll(results);
    }

    private void initPosts() {
        LocalDateTime now = LocalDateTime.now();

        // 공지
        Post n1 = postRepository.save(post(PostCategory.공지, "CodeClass 서비스 오픈 안내", "CodeClass는 알고리즘 학습을 위한 온라인 채점 플랫폼입니다. Python3, Java를 지원하며 앞으로 더 많은 언어와 문제를 추가할 예정입니다.", "운영자", "admin1234", 312, now.minusDays(30), true));
        Post n2 = postRepository.save(post(PostCategory.공지, "신규 문제 20문제 추가 안내 (그래프/트리)", "그래프와 트리 분야 문제 20문제가 추가되었습니다. 난이도는 중~상으로 구성되어 있습니다.", "운영자", "admin1234", 204, now.minusDays(20)));
        Post n3 = postRepository.save(post(PostCategory.공지, "Python3 채점 환경 업데이트 (3.11 → 3.12)", "Python 채점 환경이 3.12로 업데이트되었습니다. match 문 등 새 문법을 사용하실 수 있습니다.", "운영자", "admin1234", 178, now.minusDays(15)));
        Post n4 = postRepository.save(post(PostCategory.공지, "서버 점검 안내 (6/15 새벽 2시~4시)", "정기 서버 점검이 예정되어 있습니다. 해당 시간에는 채점 서비스를 이용하실 수 없습니다.", "운영자", "admin1234", 95, now.minusDays(10), true));
        Post n5 = postRepository.save(post(PostCategory.공지, "채점 결과 오류 수정 완료", "일부 문제에서 예제 입력 처리 오류가 있었습니다. 현재 정상 복구되었으며, 영향받은 제출 내역은 재채점 처리되었습니다.", "운영자", "admin1234", 143, now.minusDays(7)));
        Post n6 = postRepository.save(post(PostCategory.공지, "Java 채점 메모리 제한 256MB 적용 안내", "Java 제출 코드에 JVM 힙 메모리 제한(-Xmx256m)이 적용되었습니다. 과도한 메모리 사용 코드는 런타임 에러가 발생할 수 있습니다.", "운영자", "admin1234", 87, now.minusDays(3)));

        // 질문
        Post q1  = postRepository.save(post(PostCategory.질문, "피보나치 함수 메모이제이션 질문", "재귀로 피보나치를 구현했는데 시간 초과가 납니다. 메모이제이션을 어떻게 적용하면 될까요?", "kimcoding", "1234", 83, now.minusDays(25)));
        Post q2  = postRepository.save(post(PostCategory.질문, "A+B 문제에서 입력을 어떻게 받나요?", "Python에서 두 수를 한 줄에 입력받는 방법이 궁금합니다. map(int, input().split())을 써야 하나요?", "newbie123", "5678", 47, now.minusDays(22)));
        Post q3  = postRepository.save(post(PostCategory.질문, "BFS에서 방문 체크를 언제 해야 하나요?", "큐에 넣을 때 방문 처리를 해야 하는지, 꺼낼 때 해야 하는지 헷갈립니다.", "leestu", "pass1234", 61, now.minusDays(18)));
        Post q4  = postRepository.save(post(PostCategory.질문, "다익스트라 음수 간선 처리", "다익스트라 알고리즘은 음수 간선이 있으면 왜 안 되는지 이유가 궁금합니다.", "algo_noob", "qwer1234", 55, now.minusDays(16)));
        Post q5  = postRepository.save(post(PostCategory.질문, "재귀 스택 오버플로우 해결법", "N=10000 이상에서 재귀 호출 시 스택 오버플로우가 발생합니다. Python에서 어떻게 해결하나요?", "parkjava", "pass1234", 72, now.minusDays(14)));
        Post q6  = postRepository.save(post(PostCategory.질문, "유니온 파인드 경로 압축 이해가 안 됩니다", "경로 압축을 적용했을 때 왜 시간 복잡도가 줄어드는지 직관적으로 이해가 어렵습니다.", "choi99", "choi1234", 48, now.minusDays(12)));
        Post q7  = postRepository.save(post(PostCategory.질문, "세그먼트 트리 구간 업데이트 질문", "포인트 업데이트는 구현했는데 구간 업데이트는 lazy propagation을 써야 한다고 하던데, 개념이 잘 이해가 안 됩니다.", "devjay", "dev1234", 39, now.minusDays(11)));
        Post q8  = postRepository.save(post(PostCategory.질문, "투 포인터 left < right 조건 질문", "투 포인터 문제에서 while(left < right)와 while(left <= right) 중 어느 조건을 써야 하는지 혼란스럽습니다.", "hyomin_k", "hk1234", 52, now.minusDays(9)));
        Post q9  = postRepository.save(post(PostCategory.질문, "DP 테이블 초기화 값 설정 기준", "DP 풀 때 dp 배열을 0으로 초기화해야 할 때와 INF로 초기화해야 할 때의 기준이 무엇인가요?", "kimcoding", "1234", 44, now.minusDays(8)));
        Post q10 = postRepository.save(post(PostCategory.질문, "플로이드 와샬 INF 처리 질문", "플로이드 와샬에서 INF + 가중치 연산 시 오버플로우가 나는 경우가 있는데, 어떻게 방어해야 하나요?", "leestu", "pass1234", 36, now.minusDays(6)));
        Post q11 = postRepository.save(post(PostCategory.질문, "Java에서 Scanner vs BufferedReader 성능 차이", "입력이 많은 문제에서 Scanner를 쓰면 시간 초과가 나는 경우가 있는데, BufferedReader를 쓰면 해결되나요?", "javadev99", "java1234", 58, now.minusDays(5)));
        Post q12 = postRepository.save(post(PostCategory.질문, "그래프 문제에서 인접 행렬 vs 인접 리스트 선택 기준", "노드 수와 간선 수에 따라 어떤 자료구조를 선택하는 게 좋은지 알고 싶습니다.", "algo_noob", "qwer1234", 41, now.minusDays(4)));
        Post q13 = postRepository.save(post(PostCategory.질문, "KMP 실패 함수 계산 방법 이해", "KMP의 핵심인 실패 함수를 어떻게 계산하는지 예시를 들어 설명해주실 수 있나요?", "devjay", "dev1234", 29, now.minusDays(3)));
        Post q14 = postRepository.save(post(PostCategory.질문, "위상 정렬 사이클 존재 여부 판단", "위상 정렬 수행 후 사이클이 있는지 없는지 어떻게 판단하나요?", "choi99", "choi1234", 33, now.minusDays(2)));
        Post q15 = postRepository.save(post(PostCategory.질문, "이분 탐색 mid 계산 시 오버플로우 방지", "(left + right) / 2 대신 left + (right - left) / 2를 쓰는 이유가 있나요?", "newbie123", "5678", 27, now.minusDays(1)));
        Post q16 = postRepository.save(post(PostCategory.질문, "Python 재귀 제한 sys.setrecursionlimit 얼마로 설정하나요?", "DFS 풀 때마다 재귀 제한 때문에 에러가 나는데 얼마로 설정하는 게 안전한가요?", "hyomin_k", "hk1234", 65, now.minusHours(20)));
        Post q17 = postRepository.save(post(PostCategory.질문, "백트래킹과 완전 탐색의 차이가 뭔가요?", "N-Queen이 백트래킹이라고 하는데, 가지치기가 없으면 완전 탐색이고 있으면 백트래킹인가요?", "kimcoding", "1234", 38, now.minusHours(12)));
        Post q18 = postRepository.save(post(PostCategory.질문, "힙(우선순위 큐) 다익스트라 구현 질문", "Python heapq로 다익스트라 구현 중 이미 방문한 노드를 어떻게 거르나요?", "parkjava", "pass1234", 43, now.minusHours(8)));
        Post q19 = postRepository.save(post(PostCategory.질문, "LCA 희소 배열 전처리 시간 복잡도", "LCA를 희소 배열로 풀 때 전처리가 O(N log N)이라고 하는데, 실제 쿼리당 O(log N)이 되는 이유를 알고 싶습니다.", "javadev99", "java1234", 21, now.minusHours(4)));
        Post q20 = postRepository.save(post(PostCategory.질문, "연결 그래프인지 확인하는 가장 빠른 방법", "주어진 그래프가 연결 그래프인지 BFS/DFS 중 어느 방법이 더 빠른가요?", "leestu", "pass1234", 18, now.minusHours(1)));

        // 자유
        Post f1  = postRepository.save(post(PostCategory.자유, "드디어 50번 네트워크 플로우 풀었습니다!", "3일 동안 매달렸는데 드디어 AC를 받았네요. 최대 유량 알고리즘 정말 어렵네요.", "parkjava", "pass1234", 94, now.minusDays(24)));
        Post f2  = postRepository.save(post(PostCategory.자유, "알고리즘 공부 루틴 공유합니다", "저는 매일 1문제씩 풀고, 막히면 1시간 이상은 혼자 고민하는 편입니다. 여러분은 어떤 루틴으로 공부하시나요?", "choi99", "choi1234", 112, now.minusDays(21)));
        Post f2b = postRepository.save(post(PostCategory.자유, "코딩 테스트 D-30 준비 후기", "한 달 동안 매일 2문제씩 풀었습니다. 덕분에 실력이 많이 늘었어요. 다들 화이팅!", "devjay", "dev1234", 76, now.minusDays(19)));
        Post f3  = postRepository.save(post(PostCategory.자유, "Python vs Java 채점 속도 비교 해봤습니다", "같은 알고리즘을 Python3와 Java로 구현해서 제출해봤는데, Java가 평균 3배 이상 빠르더라고요.", "hyomin_k", "hk1234", 88, now.minusDays(17)));
        Post f4  = postRepository.save(post(PostCategory.자유, "처음으로 골드 문제 해결했어요", "벨만-포드 문제를 2시간 만에 혼자 풀었습니다. 예전엔 다익스트라도 어려웠는데 성장한 것 같아서 기쁩니다.", "algo_noob", "qwer1234", 67, now.minusDays(15)));
        Post f5  = postRepository.save(post(PostCategory.자유, "코딩 테스트 합격 후기 공유", "취준 6개월 만에 드디어 코딩 테스트를 통과했습니다! CodeClass에서 꾸준히 연습한 덕분인 것 같아요.", "kimcoding", "1234", 203, now.minusDays(13)));
        Post f6  = postRepository.save(post(PostCategory.자유, "알고리즘 공부 시작하기 좋은 순서 추천해드립니다", "개인적으로 구현→정렬→BFS/DFS→DP→그래프 순으로 공부하는 것을 추천합니다. 처음부터 어려운 거 하면 지치더라고요.", "leestu", "pass1234", 134, now.minusDays(11)));
        Post f7  = postRepository.save(post(PostCategory.자유, "오늘 실수로 무한 루프 코드 제출했다가 타임아웃 ㅋㅋ", "while True: 탈출 조건을 빠뜨리고 제출했습니다. 여러분도 제출 전에 탈출 조건 꼭 확인하세요!", "parkjava", "pass1234", 57, now.minusDays(9)));
        Post f8  = postRepository.save(post(PostCategory.자유, "그래프 문제 모아서 풀기 챌린지 같이 하실 분?", "이번 주 목표는 그래프 관련 문제 10문제 풀기입니다. 같이 하실 분 댓글 달아주세요!", "javadev99", "java1234", 49, now.minusDays(7)));
        Post f9  = postRepository.save(post(PostCategory.자유, "재귀 → 반복문으로 바꾸는 연습이 많이 도움됩니다", "재귀를 스택을 이용한 반복문으로 변환하는 연습을 하니까 스택 자료구조 이해도가 확 올라갔어요.", "choi99", "choi1234", 71, now.minusDays(5)));
        Post f10 = postRepository.save(post(PostCategory.자유, "슬라이딩 윈도우 문제 패턴 정리", "슬라이딩 윈도우 문제를 풀면서 패턴을 정리해봤습니다. 투 포인터랑 같이 이해하면 훨씬 편하더라고요.", "devjay", "dev1234", 62, now.minusDays(4)));
        Post f11 = postRepository.save(post(PostCategory.자유, "백준 solved.ac 티어 올리기 vs CodeClass 완주 어느게 나을까요", "둘 다 해본 입장에서 CodeClass처럼 문제가 체계적으로 분류된 게 학습에 더 효율적인 것 같습니다.", "hyomin_k", "hk1234", 38, now.minusDays(3)));
        Post f12 = postRepository.save(post(PostCategory.자유, "문제 50개 완주 후기", "드디어 등록된 문제를 전부 풀었습니다. 네트워크 플로우가 제일 어려웠고, A+B가 제일 쉬웠습니다 ㅎㅎ", "algo_noob", "qwer1234", 155, now.minusDays(2)));
        Post f13 = postRepository.save(post(PostCategory.자유, "알고리즘 스터디 멤버 모집합니다 (주 2회 온라인)", "매주 화/목 저녁 9시에 화상으로 진행합니다. 하루 1문제 이상 풀 수 있는 분 환영합니다.", "kimcoding", "1234", 84, now.minusDays(1)));
        Post f14 = postRepository.save(post(PostCategory.자유, "DP 점화식 세우는 게 제일 어렵지 않나요?", "알고리즘 중에서 DP 점화식 도출이 제일 창의력이 필요한 것 같습니다. 여러분은 어떻게 접근하시나요?", "javadev99", "java1234", 46, now.minusHours(10)));
        Post f15 = postRepository.save(post(PostCategory.자유, "오늘 처음으로 BFS로 최단 경로 구했습니다", "미로 탐색 문제를 드디어 혼자 힘으로 풀었어요! BFS가 최단 경로를 보장한다는 게 이해됐습니다.", "newbie123", "5678", 29, now.minusHours(2)));

        // 댓글
        commentRepository.save(comment(n1, "운영자", "문의사항은 게시판을 이용해주세요.", now.minusDays(29)));
        commentRepository.save(comment(n1, "kimcoding", "오픈 축하합니다!", now.minusDays(28)));
        commentRepository.save(comment(n2, "parkjava", "트리 문제 기다리고 있었습니다!", now.minusDays(19)));
        commentRepository.save(comment(n4, "choi99", "미리 공지해주셔서 감사합니다.", now.minusDays(9)));
        commentRepository.save(comment(n6, "javadev99", "메모리 제한이 생겼군요. System.gc() 호출도 안 되겠네요.", now.minusDays(2)));

        commentRepository.save(comment(q1, "leedev", "dp = {} 딕셔너리를 전역으로 선언하고 dp[n]에 결과를 저장하면 됩니다.", now.minusDays(24)));
        commentRepository.save(comment(q1, "hyojae.lee", "또는 @lru_cache 데코레이터를 사용하면 간단하게 해결됩니다.", now.minusDays(23)));
        commentRepository.save(comment(q2, "choi99", "map(int, input().split())을 쓰면 됩니다. a, b = map(int, input().split()) 이렇게요.", now.minusDays(21)));
        commentRepository.save(comment(q3, "parkjava", "큐에 넣을 때 방문 처리하는 게 맞습니다. 꺼낼 때 하면 같은 노드가 여러 번 큐에 들어갈 수 있어요.", now.minusDays(17)));
        commentRepository.save(comment(q4, "javadev99", "이미 확정된 최단 거리를 음수 간선이 다시 줄일 수 있어서 그리디 가정이 깨집니다. 그 경우엔 벨만-포드를 쓰세요.", now.minusDays(15)));
        commentRepository.save(comment(q5, "leedev", "sys.setrecursionlimit(10**6) 설정하거나, 아예 반복문으로 변환하는 게 안전합니다.", now.minusDays(13)));
        commentRepository.save(comment(q8, "choi99", "일반적으로 투 포인터에서는 left < right를, 이진 탐색에서는 left <= right를 씁니다.", now.minusDays(8)));
        commentRepository.save(comment(q11, "hyojae.lee", "맞습니다. BufferedReader + StringTokenizer 조합이 가장 빠릅니다.", now.minusDays(4)));
        commentRepository.save(comment(q15, "parkjava", "int 범위에서 left+right가 오버플로우날 수 있기 때문입니다. Java에서 특히 주의해야 해요.", now.minusDays(0)));
        commentRepository.save(comment(q17, "devjay", "맞습니다. 가지치기 유무가 핵심 차이입니다. 가지치기가 없으면 브루트포스에 가깝습니다.", now.minusHours(10)));
        commentRepository.save(comment(q18, "kimcoding", "dist[node] < cost 조건으로 이미 더 짧은 거리가 기록된 경우를 스킵하면 됩니다.", now.minusHours(6)));

        commentRepository.save(comment(f2, "kimcoding", "저도 비슷한 루틴인데, 풀이 보고 나서 꼭 직접 다시 구현해보는 게 중요한 것 같아요.", now.minusDays(20)));
        commentRepository.save(comment(f5, "운영자", "축하합니다! 앞으로도 좋은 결과 있으시길 바랍니다.", now.minusDays(12)));
        commentRepository.save(comment(f5, "devjay", "저도 열심히 해야겠네요. 동기부여 됩니다!", now.minusDays(12)));
        commentRepository.save(comment(f6, "newbie123", "감사합니다! 구현부터 시작해야겠어요.", now.minusDays(10)));
        commentRepository.save(comment(f8, "hyomin_k", "저도 참여하고 싶습니다!", now.minusDays(6)));
        commentRepository.save(comment(f8, "leestu", "저도요! 연락 방법이 있나요?", now.minusDays(6)));
        commentRepository.save(comment(f12, "운영자", "완주를 축하합니다! 곧 더 많은 문제가 추가될 예정입니다.", now.minusDays(1)));
        commentRepository.save(comment(f13, "algo_noob", "참여하고 싶습니다! 어디서 신청하면 되나요?", now.minusHours(20)));
        commentRepository.save(comment(f15, "choi99", "축하해요! BFS 익히면 최단 경로 문제 대부분 풀 수 있어요.", now.minusHours(1)));
    }

    private Post post(PostCategory category, String title, String content, String author, String password, int viewCount, LocalDateTime createdAt) {
        return post(category, title, content, author, password, viewCount, createdAt, false);
    }

    private Post post(PostCategory category, String title, String content, String author, String password, int viewCount, LocalDateTime createdAt, boolean pinned) {
        return Post.builder()
                .category(category).title(title).content(content)
                .author(author).password(password).viewCount(viewCount).createdAt(createdAt).pinned(pinned)
                .build();
    }

    private Comment comment(Post post, String author, String content, LocalDateTime createdAt) {
        return Comment.builder()
                .post(post).author(author).content(content).createdAt(createdAt)
                .build();
    }

    private Problem p(long id, String title, int solved, int submissions, String rate,
                      String description, String inputDesc, String outputDesc,
                      String exampleInput, String exampleOutput,
                      String difficulty, String algorithm) {
        return Problem.builder()
                .id(id).title(title).solved(solved).submissions(submissions).rate(rate)
                .description(description).inputDesc(inputDesc).outputDesc(outputDesc)
                .exampleInput(exampleInput).exampleOutput(exampleOutput)
                .difficulty(difficulty).algorithm(algorithm)
                .build();
    }

    private JudgeResult jr(String userId, Long problemId, String result, int memory, int time,
                           String language, int codeLength, LocalDateTime submittedAt) {
        return JudgeResult.builder()
                .userId(userId).problemId(problemId).result(result)
                .memory(memory).time(time).language(language)
                .codeLength(codeLength).submittedAt(submittedAt)
                .build();
    }
}
