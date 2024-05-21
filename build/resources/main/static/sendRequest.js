document.getElementById('sendRequest').addEventListener('click', function() {
    const userId = '1'; // 이 변수명은 백엔드의 변수명과 달라도 됩니다.
    axios.post(`https://boardpick-server.store/api/pick/${userId}`)
        .then(response => {
            console.log(response.data); // 서버로부터의 응답 출력
        })
        .catch(error => {
            console.error('에러 발생!', error);
        });
});
