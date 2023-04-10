function adding(event) {
    const form = document.getElementById(`addUserForm`);
    event.preventDefault();
    const formData = new FormData(form);
    class Role {
        constructor(id, authority) {
            this.id = id;
            this.authority = authority;
        }
    }
    let currentRoles = [];
    const roles4add = Array.from(formData.getAll('roles'))

    for(let i = 0; i<roles4add.length; i++) {
        const id = roles4add[i];
        const authority = id == 1 ? `ROLE_ADMIN` : `ROLE_USER`
        currentRoles.push(new Role(id, authority))
    }
    const user = {
        name: formData.get(`name`),
        surname: formData.get(`surname`),
        email: formData.get(`email`),
        password: formData.get(`password`),
        roles: currentRoles
    };
    fetch('http://localhost:8080/api/users/',{
        method: 'PUT',
        headers: {
            'Content-Type' : 'application/json;charset=utf-8'
        },
        body: JSON.stringify(user)
    })
        .then(response => response.json())
        .then(data => {
            console.log('ПОЛЬЗОВАТЕЛЬ ' + data.valueOf().username + ' ДОБАВЛЕН' )
            form.reset();
        })
        .catch(error => {
            console.log("ОШИБКА ДОБАВЛЕНИЯ ПОЛЬЗОВАТЕЛЯ " + error.message);
        }).finally( ()=>{redirectingToStart();});
}