function f_arr1() {
    // 리터럴 방식으로 배열 생성
    let fruits = ["사과",1,"배",true,"포도","감",null,"복숭아",undefined];
    document.querySelector('#disp').innerText = fruits;
}

// function f_arr2() {
//     // 생성자 방식으로 배열 생성
//     let fruits = new Array("참외","수박","포도","자몽");
//     let result = "";
//     for (let i = 0; i < fruits.length; i++) {
//         if (i > 0) result += "-";
//         result += fruits[i];
//     }
//     document.querySelector('div').innerText = result;
// }

function f_arr2() {
    // 생성자 방식으로 배열 생성
    let fruits = new Array();
    fruits[0] = "망고";
    fruits[1] = "두리안";
    fruits[2] = "체리";
    fruits[3] = "패션후루츠";
    fruits[4] = "메론";
    
    let result = "";
    for (x in fruits) { // 배열도 하나의 객체 (현재 배열의 index를 key정보로 활용)
        console.log(x); // index정보 반환..
        if (x > 0) result += " / ";
        result += fruits[x];
    }
    document.getElementById('disp').innerText = result;

}