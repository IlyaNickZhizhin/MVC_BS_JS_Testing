console.log("changing5.8");
$(document).on('click', '#changeConfirm', function () {
    let id = document.getElementById('id').value;
    let tr = document.getElementById('tr№'+id);
    // получаем массив int из формы select по id="roles" и name="roles4change"
    let roles = $('#roles').val();
    class Role {
        constructor(id, authority) {
            this.id = id;
            this.authority = authority;
        }
    }
    let currentRoles = [];
    for(let i = 0; i<roles.length; i++) {
        const id = roles[i];
        const authority = id == 1 ? `ROLE_ADMIN` : `ROLE_USER`
        currentRoles.push(new Role(id, authority))
    }
    const user = {
        id: document.getElementById('id').value,
        name: document.getElementById('name').value,
        surname: document.getElementById('surname').value,
        email: document.getElementById('email').value,
        password: document.getElementById('pass').value,
        roles: currentRoles
    };
    fetch('http://localhost:8080/api/users/',{
        method: 'PATCH',
        headers: {
            'Content-Type' : 'application/json;charset=utf-8'
        },
        body: JSON.stringify(user)
    })
        .then(response => response.json())
        .then(data => {
            console.log('ПОЛЬЗОВАТЕЛЬ №' + id + 'ИЗМЕНЕН')
            console.log(data);
            let bodyRolesString = '';
            for (let a = 0; a < data.authorities.length; a++) {
                bodyRolesString += data.authorities[a].authority.substring(5);
                bodyRolesString += a<(data.authorities.length-1) ? ", " : "";
            }
            console.log(bodyRolesString);
            tr.innerHTML = `
                <td id="id:${data.id}">${data.id}</td>
                <td id="name:${data.id}">${data.name}</td>
                <td id="surname:${data.id}">${data.surname}</td>
                <td id="username:${data.id}">${data.username}</td>
                <td id="roles:${data.id}">${bodyRolesString}</td>
                <td>
                    <div class="all-classes-container">
                    <button id="changeButton" type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                            data-target="#changeModal" data-userID="${data.id}">
                        Изменить
                    </button>
                </div>
                </td>
                <td>
                    <button id="deleteButton" type="button" class="btn btn-danger btn-sm" data-toggle="modal"
                            data-target="#deleteModal" data-userID="${data.id}">
                        Удалить
                    </button>
                </td>`
            console.log(tr.innerHTML);
        })
        .catch(error => {
            console.log("ОШИБКА, ПОЛЬЗОВАТЕЛЬ №" + id + "НЕ ИЗМЕНЕН" + error.message());
        });
});

/*    let bodyRolesString = '';
    for (let a = 0; a < data.roles.length; a++) {
    bodyRolesString += data.roles[a].authority.substring(5);
    bodyRolesString += a<(data.roles.length-1) ? ", " : "";
}
console.log(bodyRolesString);
tr.innerHTML = `
    <td>${user.id}</td>
    <td>${user.name}</td>
    <td>${user.surname}</td>
    <td>${user.email}</td>
    <td>${bodyRolesString}</td>
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
    `*/