function drawChangeModal(id) {

    console.log('Кнопка ИЗМЕНИТЬ пользователя №' + id + ' нажата!');
    let url = 'http://localhost:8080/api/users/' + id;
    let body = ''
         fetch(url)
        .then(response => response.json())
        .then(data => changingData(data))
        .catch(error => console.log(error))
    const changingData = (data) => {
             console.log(data)

        let rolesID =[]
        data.authorities.forEach((id)=>{
             rolesID.push(id.id);
         })

        body += `
        <form id="addUserForm" class="needs-validation">
             <input id='id' type="hidden" value="${data.id}">
           <div class="form-group">
             <p class="text-center my-0"><strong> Имя </strong></p>
             <input class="form-control mt-0" type="text" id="name" value="${data.name}"/>
           </div>
           <div class="form-group align-items-center justify-content-center">
             <p class="text-center my-0"><strong> Фамилия </strong></p>
             <input class="form-control mt-0" type="text" id="surname" value="${data.surname}"/>
           </div>
           <div class="form-group">
             <p class="text-center my-0"><strong> Электронная почта </strong></p>
             <input class="form-control mt-0" type="text" id="email" value="${data.username}"/>
           </div>
           <div class="form-group">
             <p class="text-center my-0"><strong> Пароль</strong></p>
             <div>
               <input class="form-control w-100 mt-0" type="password" id="pass" value="${data.password}" name="pass"/>
             </div>
           </div>
           <div class="form-group">
             <p class="text-center my-0"><strong> Роль</strong></p>
             <select multiple class="form-control p-0 m-0" id="roles" name="roles4change">
               <option value="1" ${rolesID.includes(1) ? 'selected' : ''}>ADMIN</option>
               <option value="2" ${rolesID.includes(2) ? 'selected' : ''}>USER</option>
             </select>
           </div>
        </form>`
        document.getElementById('changing').innerHTML = body;
    }
}
