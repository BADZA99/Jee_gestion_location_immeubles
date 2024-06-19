<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="../../header.jsp" />
  <%
       String status = (String) request.getAttribute("status");
    %>
<div class="p-10">
    <h1 class="mb-8 font-extrabold text-4xl">Register</h1>
    <div class="grid grid-cols-1 md:grid-cols-2 gap-8">

        <form action="AuthServlet-servlet" method="post">
            <div>
                 <input type="hidden" name="action" value="register">
                 <input type="hidden" id="status" value="<%=status%>">
                <label class="block font-semibold" for="nom">Nom</label>
                <input class="w-full shadow-inner bg-gray-100 rounded-lg placeholder-black text-2xl p-4 border-none block mt-1 w-full" id="nom" type="text" name="nom" required="required" autofocus="autofocus">
            </div>


            <div class="flex w-full justify-between mt-4 items-center">
   <span>je m'inscrit en tant que </span>
<select class="w-full/3 p-2 border-2 " name="role" >
  <option value=""></option>
  <option value="LOCATAIRE">LOCATAIRE</option>
  <option value="PROPRIETAIRE">PROPRIETAIRE</option>
</select>
            </div>
         
            <div class="mt-4">
                <label class="block font-semibold" for="mdp">Mot de passe</label>
                <input class="w-full shadow-inner bg-gray-100 rounded-lg placeholder-black text-2xl p-4 border-none block mt-1 w-full" id="mdp" type="password" name="mdp" required="required" autocomplete="new-password">
            </div>

            <div class="flex items-center justify-between mt-8">
                <button type="submit" class="flex items-center justify-center px-8 py-3 border border-transparent text-base font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 md:py-4 md:text-lg md:px-10">Inscription</button>
                <a class="font-semibold" href="index-servlet?action=login">
                    deja inscrit ?
                </a>
            </div>
        </form>

        <aside class="">
            <div class="bg-gray-100 p-8 rounded">
                <h2 class="font-bold text-2xl">Instructions</h2>
                <ul class="list-disc mt-4 list-inside">
                    <li>All users must provide a valid email address and password to create an account.</li>
                    <li>Users must not use offensive, vulgar, or otherwise inappropriate language in their username or profile information</li>
                    <li>Users must not create multiple accounts for the same person.</li>
                </ul>
            </div>
        </aside>

    </div>
</div>

<script src="
https://cdn.jsdelivr.net/npm/sweetalert2@11.12.0/dist/sweetalert2.all.min.js
"></script>
<link href="
https://cdn.jsdelivr.net/npm/sweetalert2@11.12.0/dist/sweetalert2.min.css
" rel="stylesheet">

<script>
    const status = document.getElementById('status').value;
 const nom = document.getElementById('nom').value;
    const mdp = document.getElementById('mdp').value;

    if (status === 'vides') {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'veullez remplir tous les champs!',
        });
    }
    if (status === 'error') {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Something went wrong!',
        });
    }
    else if (status === 'success') {
        Swal.fire({
            icon: 'success',
            title: 'Success',
            text: 'User created successfully!',
        });
    }

</script>
