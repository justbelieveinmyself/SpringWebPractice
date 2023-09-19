<#macro page>

<!DOCTYPE html>

<html lang="en">

    <head>

        <title>Spring~</title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

        <link rel="stylesheet" href="/static/style.css"/>

        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <script src="https://kit.fontawesome.com/7d8b87234d.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/turbolinks/5.2.0/turbolinks.js" integrity="sha512-G3jAqT2eM4MMkLMyQR5YBhvN5/Da3IG6kqgYqU9zlIH4+2a+GuMdLb5Kpxy6ItMdCfgaKlo2XFhI0dHtMJjoRw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous"/>
        <script defer="1" src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://www.google.com/recaptcha/api.js"></script>

    </head>
    <body>
        <#include "navbar.ftlh">
        <div class="container">
            <#nested>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"> </script>
    </body>
</html>
</#macro>