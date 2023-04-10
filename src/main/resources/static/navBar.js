fetch('http://localhost:8080/api/user')
    .then(response => response.json())
    .then(data => {
        bodyRoles = []
        data.authorities.forEach(role => {
           bodyRoles.push(role.authority.substring(5))
        });
        document.getElementById("navbar").innerHTML =
           `<span style="font-weight: bolder">${data.username}</span>
            <span> with roles: </span>
            <span>${bodyRoles}</span>`;
    })
    .catch(error => console.log(error));
