// 1. 1부터 10까지의 합을 구하는 프로그램을 작성하시오.
function f_mun1() {
    let sum = 0;
    for (let i = 1; i <= 10; i++) {
        sum += i;
    }
    document.querySelector('#disp').innerText = sum;
}

// 2. 1부터 200까지의 짝수의 합을 구하는 프로그램을 작성하시오. (continue를 이용)
function f_mun2() {
    let sum = 0;
    for (let i = 1; i <= 200; i++) {
        if ((i % 2) === 1) continue;
        sum += i;
    }
    document.querySelector('#disp').innerText = sum;
}

// 3. 사용자가 입력한 값을 계속 더하고, 사용자가 0을 입력하면 그때까지 누적된 값을 출력하는 프로그램을 작성하시오.
function f_mun3() {

    let sum = 0; // 더한 값을 담을 공간
    let str = ""; // 입력된 값을 담을 공간
    let result = "";
    // 무한반복
    while (1) {
        let x = parseInt(prompt("숫자입력 (0입력시종료)")); // string 타입 반환하므로 정수형태로 변환

        if (x === 0) break; // 무한반복 빠져나갈 조건

        sum += x;
        str += x + " "; // 입력된 값 구분을 위해 입력 값 사이 공백 표현

        result = "입력된 값 : " + str + "<br>" + "더해진 값 : " + sum;
    }
    document.querySelector('div').insertAdjacentHTML('afterbegin', result); // 요소 안쪽 앞에 result값 대입
}

// 4. 다중 for문을 이용해서 1~10까지 중 i와 k의 더한 합이 3의 배수일 때만 출력 (continue 이용)
function f_mun4() {
    let result = "";
    for (let i = 1; i <= 10; i++) {
        for (let k = 1; k <= 10; k++) {
            if ((i + k) % 3 !== 0) continue;
            result += i + " + " + k + " = " + (i + k) + "<br>";
        }
    }
    document.querySelector('div').insertAdjacentHTML('beforeend', result); // 요소 안쪽 뒤에 삽입
}

// 5. 1~100까지 중 2의 배수이면서 3의 배수인것만 출력
function f_mun5() {
    let result = "";
    for (let i = 1; i <= 100; i++) {
        if ((i % 2 === 0) && (i % 3 === 0)) {
            if (result.length > 0) result += ", ";
            result += i;
        }
    }
    document.querySelector('#disp').innerText = result;
}

// 6. 두 수를 입력(prompt) 두수의 합이 100이상일때만 출력(continue를 이용, 두수 모두 0이 입력되면 종료)
function f_mun6() {

    let result = "";
    while (true) {
        let a = parseInt(prompt("첫번째 수를 입력하세요"));
        let b = parseInt(prompt("두번째 수를 입력하세요"));
        if (a === 0 && b === 0) break;

        let sum = a + b;
        if (sum < 100) continue;
        result += `${a} + ${b} = ${sum} <br>`;
    }
    // document.querySelector('#disp').innerHTML = result;
    document.querySelector('div').insertAdjacentHTML('beforeend', result);
}