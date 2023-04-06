const form = document.getElementById(`addUserForm`);
console.log("Adding was started")
form.addEventListener(`submit`, adding);

function adding(event) {
    console.log("Change func")
    event.preventDefault();
    const formData = new FormData(form);
    class Role {
        constructor(id, authority) {
            this.id = id;
            this.authority = authority;
        }
    }
    let currentRoles = [];
    const roles = Array.from(formData.getAll('roles'))

    for(let i = 0; i<roles.length; i++) {
        const id = roles[i];
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
            console.log('ПОЛЬЗОВАТЕЛЬ ДОБАВЛЕН' + data.value)
            form.reset();
            window.location.href = 'http://localhost:8080/admin';
        })
        .catch(error => {
            console.log("ОШИБКА, ПОЛЬЗОВАТЕЛЬ НЕ ДОБАВЛЕН" + error.message());
        });
}

console.log("Adding was ended")