let url = 'http://localhost:8080/api/users'

fetch(url)
    .then(response => response.json())
    .then(data => fillingAll(data))
    .catch(error => console.log(error))

function fillingAll(data) {
    let body = `<ui class="nav nav-tabs">
    
        <li class="nav-item">
            <a class="nav-link active" href="/admin" th:method="get" style="color:black">Пользователи</a>
        </li>
        <li class="nav-item">
            <a onclick="fillingAddForm()" class="nav-link" style="color:blue">Добавить</a>
        </li>
        </ui>
        <div class="container shadow-lg p3 bg-body rounded">
            <h5 style="font-weight: bold">
                Все пользователи
            </h5>
        </div>
        <div class="container shadow-lg p3 mb-5 bg-body rounded">
            <table class="table bg-white table-striped table-borderless border-top w-90">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Имя</th>
                    <th>Фамилия</th>
                    <th>Электронная почта</th>
                    <th>Роль</th>
                    <th>Изменить</th>
                    <th>Удалить</th>
                </tr>
                </thead>
                <tbody>`
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
                                data-target="#changeModal" onclick="drawChangeModal(${data[i].id})">
                            Изменить
                        </button>
                    </div>
                    </td>
                    <td>
                        <button id="deleteButton" type="button" class="btn btn-danger btn-sm" data-toggle="modal"
                                data-target="#deleteModal" onclick="drawDeleteModal(${data[i].id})">
                            Удалить
                        </button>
                    </td>
                 </tr>`
    }
    body += `</tbody></table></div>`
    document.getElementById('adminPanel').innerHTML = body;
}


