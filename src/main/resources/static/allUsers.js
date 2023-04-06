let url = 'http://localhost:8080/api/users'
fetch(url)
    .then(response => response.json())
    .then(data => savingData(data))
    .catch(error => console.log(error))

const savingData = (data) => {
    console.log(data)
    let body = ""
    for (let i = 0; i < data.length; i++) {
        let roles = data[i].authorities;
        let bodyRolesString = '';
        for (let a = 0; a < roles.length; a++) {
            bodyRolesString += roles[a].authority.substring(5);
            bodyRolesString += a<(roles.length-1) ? ", " : "";
        }
        body += `<tr id="tr№${data[i].id}">
                        <td id="id:${data[i].id}">${data[i].id}</td>
                        <td id="name:${data[i].id}">${data[i].name}</td>
                        <td id="surname:${data[i].id}">${data[i].surname}</td>
                        <td id="username:${data[i].id}">${data[i].username}</td>
                        <td id="roles:${data[i].id}">${bodyRolesString}</td>
                        <td>
                            <div class="all-classes-container">
                            <button id="changeButton" type="button" class="btn btn-primary btn-sm" data-toggle="modal" 
                                    data-target="#changeModal" data-userID="${data[i].id}">
                                Изменить
                            </button>
                        </div>
                        </td>
                        <td>
                            <button id="deleteButton" type="button" class="btn btn-danger btn-sm" data-toggle="modal"
                                    data-target="#deleteModal" data-userID="${data[i].id}">
                                Удалить
                            </button>
                        </td>
                     </tr>`
    }
    document.getElementById('table').innerHTML = body;
}