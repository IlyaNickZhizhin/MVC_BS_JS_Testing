function deleteUserAction() {
    let id = document.getElementById('id').value;
    let tr = document.getElementById('tr№'+id);
    fetch(('http://localhost:8080/api/users/' + id),{
        method: 'DELETE',
    })
        .then(response => response.json())
        .then(() => {
            console.log('ПОЛЬЗОВАТЕЛЬ № ' + id + 'УДАЛЕН')
            tr.remove();
        })
        .catch(error => {
            console.log("ОШИБКА, ПОЛЬЗОВАТЕЛЬ №" + id + "НЕ УДАЛЕН " + error.message);
        });
}