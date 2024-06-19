<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="../../header.jsp" />
 <%
       String status = (String) request.getAttribute("status");
    %>
<div class="py-16">
    <div class="flex bg-white rounded-lg shadow-lg overflow-hidden mx-auto max-w-sm lg:max-w-4xl">
        <div class="hidden lg:block lg:w-1/2 bg-cover"
            style="background-image:url('https://images.unsplash.com/photo-1546514714-df0ccc50d7bf?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=667&q=80')">
        </div>
        <div class="w-full p-8 lg:w-1/2">
            <h2 class="text-2xl font-semibold text-gray-700 text-center">Brand</h2>
            <p class="text-xl text-gray-600 text-center">Welcome back!</p>
      
            <div class="mt-4 flex items-center justify-between">
                <span class="border-b w-1/5 lg:w-1/4"></span>
                
                <span class="border-b w-1/5 lg:w-1/4"></span>
            </div>
            <form action="AuthServlet-servlet" method="post">
            <div class="mt-4">
                   <input type="hidden" name="action" value="login">
                   <input type="hidden" id="status" value="<%=status%>">
                <label class="block text-gray-700 text-sm font-bold mb-2">Nom Utilisateur</label>
                <input class="bg-gray-200 text-gray-700 focus:outline-none focus:shadow-outline border border-gray-300 rounded py-2 px-4 block w-full appearance-none" name="name" type="text" />
            </div>
            <div class="mt-4">
                <div class="flex justify-between">
                    <label class="block text-gray-700 text-sm font-bold mb-2">Password</label>
                 
                </div>
                <input class="bg-gray-200 text-gray-700 focus:outline-none focus:shadow-outline border border-gray-300 rounded py-2 px-4 block w-full appearance-none" name="mdp" type="password" />
            </div>
            <div class="mt-8">
                <button class="bg-gray-700 text-white font-bold py-2 px-4 w-full rounded hover:bg-gray-600" type="submit">Login</button>
            </div>
            <div class="mt-4 flex items-center justify-between">
                <span class="border-b w-1/5 md:w-1/4"></span>
                <a href="#" class="text-xs text-gray-500 uppercase">or sign up</a>
                <span class="border-b w-1/5 md:w-1/4"></span>
            </div>
            </form>
        </div>
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
            text: 'nom utilisateur et/ou mot de passe incorrect',
        });
    }
    else if (status === 'success') {
        Swal.fire({
            icon: 'success',
            title: 'Success',
            text: 'connexion reussie',
        });
    }

</script>
