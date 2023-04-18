// 랜덤을 발생시키는 함수 선언
// 65~100범위의 랜덤수를 발생시켜라
function f_random(p_str, p_end) {
    return Math.floor(Math.random() * (p_end - p_str + 1) + p_str);
}