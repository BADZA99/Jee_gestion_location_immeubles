<%@ page import="java.util.List" %>
<%@ page import="sn.dev.jee_locations_immeubles.Entities.Utilisateur" %>
<%@ page import="sn.dev.jee_locations_immeubles.Entities.Immeuble" %>
<%@ page import="sn.dev.jee_locations_immeubles.Entities.Unitelocation" %>
<%@ page import="sn.dev.jee_locations_immeubles.Entities.Demandelocation" %>
<%@ page import="sn.dev.jee_locations_immeubles.Entities.Contratlocation" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Utilisateur connctedUser = (Utilisateur) session.getAttribute("user");
    List<Immeuble> ALLimmeubles = (List<Immeuble>) request.getAttribute("ALLimmeubles");
    List<Unitelocation> allUniteLocations = (List<Unitelocation>) request.getAttribute("AllUniteLocations");
    List<Utilisateur> AllUtilisateurs = (List<Utilisateur>) request.getAttribute("AllUtilisateurs");
    List<Demandelocation> AllDemandelocation = (List<Demandelocation>) request.getAttribute("AllDemandelocation");
    List<Contratlocation> AllContratlocation = (List<Contratlocation>) request.getAttribute("AllContratlocation");
%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style type="text/tailwindcss">
        @layer utilities {
            .content-auto {
                content-visibility: auto;
            }
        }
    </style>
</head>
<body>
<!-- nav bar section -->
<nav class="flex flex-wrap items-center justify-between p-3 bg-[#e8e8e5]">
    <div class="text-xl">BNLocation</div>
    <div class="flex md:hidden">
        <button id="hamburger">
            <img class="toggle block" src="https://img.icons8.com/fluent-systems-regular/2x/menu-squared-2.png" width="40" height="40" />
            <img class="toggle hidden" src="https://img.icons8.com/fluent-systems-regular/2x/close-window.png" width="40" height="40" />
        </button>
    </div>
    <div class=" toggle hidden w-full md:w-auto md:flex text-right text-bold mt-5 md:mt-0 md:border-none">
        <a href="#home" class="block md:inline-block hover:text-blue-500 px-3 py-3 md:border-none">Home
        </a>
        <a href="index-servlet?action=listeImmeubles" class="block md:inline-block hover:text-blue-500 px-3 py-3 md:border-none">Immeubles
        </a>
        <a href="#aboutus" class="block md:inline-block hover:text-blue-500 px-3 py-3 md:border-none">About us
        </a>

    </div>

    <div class="toggle w-full text-end hidden md:flex md:w-auto px-2 py-2 md:rounded">
        <a href="AuthServlet-servlet?action=logout">
            <div class="flex justify-end">
                <div class="flex items-center h-10 w-30 rounded-md bg-[#c8a876] text-white font-medium p-2">
                    logout
                </div>
            </div>
        </a>

    </div>

</nav>

<div class="bg-gray-100">
    <div class="h-screen flex overflow-hidden bg-black-200">
        <!-- Sidebar -->
        <div class="absolute bg-gray-800 text-white w-56 min-h-screen overflow-y-auto sidebar transform -translate-x-full ease-in-out duration-300"
             id="sidebar">
            <div class="p-4">
                <h1 class="text-2xl font-semibold">Sidebar</h1>
                <ul class="mt-4">
                    <li class="mb-2"><a href="#" class="block hover:text-indigo-400" data-page="home">Modifier mes infos</a></li>
                    <li class="mb-2"><a href="#" class="block hover:text-indigo-400" data-page="about">Mes infos</a></li>
                    <li class="mb-2"><a href="#" class="block hover:text-indigo-400" data-page="contact">Les Immeubles</a></li>
                    <li class="mb-2"><a href="#" class="block hover:text-indigo-400" data-page="services">Les Unites locations</a></li>
                    <li class="mb-2"><a href="#" class="block hover:text-indigo-400" data-page="demandesloc">Les demandes de locations</a></li>
                    <li class="mb-2"><a href="#" class="block hover:text-indigo-400" data-page="contratLoc">Les contrat de locations</a></li>
                    <li class="mb-2"><a href="#" class="block hover:text-indigo-400" data-page="AjoutU">Ajouter une Unite location</a></li>
                    <li class="mb-2"><a href="#" class="block hover:text-indigo-400" data-page="addImm">Ajouter Immeuble</a></li>
                    <li class="mb-2"><a href="#" class="block hover:text-indigo-400" data-page="addUser">Ajouter UN UTILISATEUR</a></li>
                </ul>
            </div>
        </div>

        <!-- Content -->
        <div class="flex-1 flex flex-col overflow-hidden">
            <!-- Navbar -->
            <div class="bg-white shadow">
                <div class="container mx-auto">
                    <div class="flex justify-between items-center py-4 px-2">
                        <h1 class="text-xl font-semibold">WELCOME Admin <%=connctedUser.getNomUtilisateur()%></h1>
                        <button class="text-gray-500 hover:text-gray-600" id="open-sidebar">
                            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16"></path>
                            </svg>
                        </button>
                    </div>
                </div>
            </div>

            <!-- Content Body -->
            <div class="flex-1 overflow-auto p-4" id="content">
                <table class="min-w-full divide-y divide-gray-200">
                    <thead>
                    <tr>
                        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Nom Utilisteur</th>
                        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Role</th>
                        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Action</th>
                    </tr>
                    </thead>
                    <tbody class="bg-white divide-y divide-gray-200">
                    <% for (Utilisateur utilisateur : AllUtilisateurs) { %>
                    <tr>
                        <td class="px-6 py-4 whitespace-nowrap"><%= utilisateur.getNomUtilisateur() %></td>
                        <td class="px-6 py-4 whitespace-nowrap"><%= utilisateur.getRole() %></td> <!-- Assuming you have a getRole method -->
                        <td class="px-6 py-4 whitespace-nowrap">
                            <a href="Admin-servlet?action=updateUser&idUser=<%= utilisateur.getId() %>" class="px-4 py-2 cursor-pointer font-medium text-white bg-blue-600 rounded-md hover:bg-blue-500 focus:outline-none focus:shadow-outline-blue active:bg-blue-600 transition duration-150 ease-in-out">Edit</a>
                            <a href="Admin-servlet?action=deleteUser&idUser=<%= utilisateur.getId() %>" class="ml-2 px-4 cursor-pointer py-2 font-medium text-white bg-red-600 rounded-md hover:bg-red-500 focus:outline-none focus:shadow-outline-red active:bg-red-600 transition duration-150 ease-in-out">Delete</a>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>


            </div>
        </div>
    </div>
</div>

<script>
    const sidebar = document.getElementById('sidebar');
    const openSidebarButton = document.getElementById('open-sidebar');
    const content = document.getElementById('content');

    openSidebarButton.addEventListener('click', (e) => {
        e.stopPropagation();
        sidebar.classList.toggle('-translate-x-full');
    });

    document.addEventListener('click', (e) => {
        if (!sidebar.contains(e.target) && !openSidebarButton.contains(e.target)) {
            sidebar.classList.add('-translate-x-full');
        }
    });

    const pages = {
        home: `
            <form action="Admin-servlet" method="post" class="w-[60%] mx-auto">
            <div>
                 <input type="hidden" name="action" value="updateAdmin">
                  <input type="hidden" name="idAdmin" value="<%=connctedUser.getId()%>">

                <label class="block font-semibold" for="nom">Nom</label>
                <input class="w-full shadow-inner bg-gray-100 rounded-lg placeholder-black text-2xl p-4 border-none block mt-1 w-full" id="nom" type="text" name="nom" required="required" autofocus="autofocus" value="<%=connctedUser.getNomUtilisateur()%>">
            </div>

            <div class="mt-4">
                <label class="block font-semibold" for="mdp">Mot de passe</label>
                <input class="w-full shadow-inner bg-gray-100 rounded-lg placeholder-black text-2xl p-4 border-none block mt-1 w-full" id="mdp" type="password" name="mdp" required="required" autocomplete="new-password">
            </div>

            <div class="flex items-center justify-between mt-8">
                <button type="submit" class="flex items-center justify-center px-8 py-3 border border-transparent text-base font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 md:py-4 md:text-lg md:px-10">Enregistrer</button>

            </div>
        </form>

      `,
        AjoutU: `
          <div class="bg-white  border-4 rounded-lg shadow relative m-10">

    <div class="flex items-start justify-between p-5 border-b rounded-t">
        <h3 class="text-xl font-semibold">
            Modifier Immeuble
        </h3>
        <button type="button" class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center" data-modal-toggle="product-modal">
           <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
        </button>
    </div>

    <div class="p-6 space-y-6">
      <form action="Admin-servlet" method="post">
    <input type="hidden" name="action" value="AjoutU">
    <input type="hidden" name="idIU" value="">
    <div class="grid grid-cols-6 gap-6">
        <div class="col-span-6 sm:col-span-3">
            <label for="product-name" class="text-sm font-medium text-gray-900 block mb-2">Nombre pièce</label>
            <input type="text" name="nbpieces" id="product-name" class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5" required="">
        </div>
        <div class="col-span-6 sm:col-span-3">
            <label for="category" class="text-sm font-medium text-gray-900 block mb-2">Loyer</label>
            <input type="text" name="loyer" id="category" class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5" placeholder="Loyer" value="">
        </div>
        <div class="col-span-6 sm:col-span-3">
            <label for="brand" class="text-sm font-medium text-gray-900 block mb-2">Image</label>
            <input type="text" name="image" id="brand" class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5" placeholder="Image URL" required="">
        </div>
        <div class="col-span-6 sm:col-span-3">
            <label for="price" class="text-sm font-medium text-gray-900 block mb-2">Superficie</label>
            <input type="text" name="superficie" id="price" class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5" placeholder="Superficie" required="">
        </div>
        <div class="col-span-full">
            <label for="immeuble-select" class="text-sm font-medium text-gray-900 block mb-2">Sélectionnez un immeuble</label>
            <select name="immeubleId" id="immeuble-select" class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5">
                <%
                    for (Immeuble immeuble : ALLimmeubles) {
                %>
                    <option value="<%= immeuble.getId() %>"><%= immeuble.getNom() %></option>
                <%
                    }
                %>
            </select>
        </div>
        <div class="col-span-full">
            <label for="product-details" class="text-sm font-medium text-gray-900 block mb-2">Numéro unité</label>
            <textarea id="product-details" name="numUnite" rows="6" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-4"></textarea>
        </div>

    </div>
    <div class="p-6 border-t border-gray-200 rounded-b">
        <button class="text-white bg-cyan-600 hover:bg-cyan-700 focus:ring-4 focus:ring-cyan-200 font-medium rounded-lg text-sm px-5 py-2.5 text-center" type="submit">Enregistrer</button>
    </div>
</form>




</div>


      `,

        addUser: `
          <div class="p-10">
    <h1 class="mb-8 font-extrabold text-4xl">Register</h1>
    <div class="grid grid-cols-1 md:grid-cols-2 gap-8">

        <form action="AuthServlet-servlet" method="post">
            <div>
                <input type="hidden" name="action" value="registerUser">

                <label class="block font-semibold" for="nom">Nom</label>
                <input class="w-full shadow-inner bg-gray-100 rounded-lg placeholder-black text-2xl p-4 border-none block mt-1 w-full" id="nom" type="text" name="nom" required="required" autofocus="autofocus">
            </div>


            <div class="flex w-full justify-between mt-4 items-center">
                <span>AJOUTER UN </span>
                <select class="w-full/3 p-2 border-2 " name="role" >
                    <option value="ADMIN">ADMIN</option>
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

                    <li>Users must not use offensive, vulgar, or otherwise inappropriate language in their username or profile information</li>
                    <li>Users must not create multiple accounts for the same person.</li>
                </ul>
            </div>
        </aside>

    </div>
</div>


      `,

        addImm: `
            <form action="Admin-servlet" method="post" class="w-[60%] mx-auto"  enctype="multipart/form-data">
            <div>
                 <input type="hidden" name="action" value="addImm">
                <label class="block font-semibold" for="nom">Nom</label>
                <input class="w-full shadow-inner bg-gray-100 rounded-lg placeholder-black text-2xl p-4 border-none block mt-1 w-full" id="nom" type="text" name="nom" required="required" autofocus="autofocus" value="">
            </div>

            <div class="mt-4">
                <label class="block font-semibold" for="Adresse">Adresse</label>
                <input class="w-full shadow-inner bg-gray-100 rounded-lg placeholder-black text-2xl p-4 border-none block mt-1 w-full" id="Adresse" type="text" name="Adresse" required="required" autocomplete="new-password">
            </div>

              <div class="mt-4">
                <label class="block font-semibold" for="Description">Description</label>
                <input class="w-full shadow-inner bg-gray-100 rounded-lg placeholder-black text-2xl p-4 border-none block mt-1 w-full" id="Description" type="text" name="Description" required="required" autocomplete="new-password">
            </div>

            <div class="mt-4">
                <label class="block font-semibold" for="equipements">equipements</label>
                <input class="w-full shadow-inner bg-gray-100 rounded-lg placeholder-black text-2xl p-4 border-none block mt-1 w-full" id="equipements" type="text" name="equipements" required="required" autocomplete="new-password">
            </div>

            <div class="mt-4">
                <label class="block font-semibold" for="image">image</label>
                <input class="w-full shadow-inner bg-gray-100 rounded-lg placeholder-black text-2xl p-4 border-none block mt-1 w-full" id="image" type="file" name="image" required="required" autocomplete="new-password">
            </div>

            <div class="flex items-center justify-between mt-8">
                <button type="submit" class="flex items-center justify-center px-8 py-3 border border-transparent text-base font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 md:py-4 md:text-lg md:px-10">Enregistrer</button>

            </div>
        </form>

      `,

        about: `
       <div class="bg-white overflow-hidden shadow rounded-lg border">
    <div class="px-4 py-5 sm:px-6">
        <h3 class="text-lg leading-6 font-medium text-gray-900">
            User Profile
        </h3>
        <p class="mt-1 max-w-2xl text-sm text-gray-500">
            This is some information about the user.
        </p>
    </div>
    <div class="border-t border-gray-200 px-4 py-5 sm:p-0">
        <dl class="sm:divide-y sm:divide-gray-200">
            <div class="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                <dt class="text-sm font-medium text-gray-500">
                    Full name
                </dt>
                <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
               <%=connctedUser.getNomUtilisateur()%>
                </dd>
            </div>
            <div class="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                <dt class="text-sm font-medium text-gray-500">
                    Role
                </dt>
                <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                   <%=connctedUser.getRole()%>
                </dd>
            </div>
        </dl>
    </div>
</div>
      `,
        services: `
   <table class="min-w-full divide-y divide-gray-200">
    <thead>
        <tr>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">N° unite</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Nombre pieces</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">superficie</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">loyer</th>

            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Image</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Action</th>
        </tr>
    </thead>
    <tbody class="bg-white divide-y divide-gray-200">
        <% for (Unitelocation unite : allUniteLocations) { %>
        <tr>
            <td class="px-6 py-4 whitespace-nowrap"><%= unite.getNumeroUnite() %></td>
            <td class="px-6 py-4 whitespace-nowrap"><%= unite.getNombrePieces() %></td>
            <td class="px-6 py-4 whitespace-nowrap"><%= unite.getSuperficie() %></td>
            <td class="px-6 py-4 whitespace-nowrap"><%= unite.getLoyer() %></td>
            <td class="px-6 py-4 whitespace-nowrap">
                <img src="<%= unite.getImage() %>" alt="Image" class="w-20 h-20">
            </td>
            <td class="px-6 py-4 whitespace-nowrap">
                <a href="Admin-servlet?action=updateU&UId=<%= unite.getId() %>" class="px-4 py-2 cursor-pointer font-medium text-white bg-blue-600 rounded-md hover:bg-blue-500 focus:outline-none focus:shadow-outline-blue active:bg-blue-600 transition duration-150 ease-in-out">Edit</a>
                <a href="Admin-servlet?action=deleteU&UId=<%= unite.getId() %>" class="ml-2 px-4 cursor-pointer py-2 font-medium text-white bg-red-600 rounded-md hover:bg-red-500 focus:outline-none focus:shadow-outline-red active:bg-red-600 transition duration-150 ease-in-out">Delete</a>
            </td>
        </tr>
        <% } %>
    </tbody>
</table>

`,

        demandesloc: `
  <table class="min-w-full divide-y divide-gray-200">
    <thead>
        <tr>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Date demande</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Statut</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">locataire id</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">unite location id</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Action</th>
        </tr>
    </thead>
    <tbody class="bg-white divide-y divide-gray-200">
        <% for (Demandelocation unite : AllDemandelocation) { %>
        <tr>
            <td class="px-6 py-4 whitespace-nowrap"><%= unite.getDateDemande() %></td>
            <td class="px-6 py-4 whitespace-nowrap"><%= unite.getStatut() %></td>
            <td class="px-6 py-4 whitespace-nowrap"><%= unite.getLocataireId() %></td>
            <td class="px-6 py-4 whitespace-nowrap"><%= unite.getUniteLocationId() %></td>
            <td class="px-6 py-4 whitespace-nowrap">
                <a href="Admin-servlet?action=updateDemande&demandeId=<%= unite.getId() %>" class="px-4 py-2 cursor-pointer font-medium text-white bg-blue-600 rounded-md hover:bg-blue-500 focus:outline-none focus:shadow-outline-blue active:bg-blue-600 transition duration-150 ease-in-out">Edit</a>

            </td>
        </tr>
        <% } %>
    </tbody>
</table>

`,
        contratLoc: `
   <table class="min-w-full divide-y divide-gray-200">
    <thead>
        <tr>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Date debut</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Date fin</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">unite location id</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">locataire id</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Action</th>
        </tr>
    </thead>
    <tbody class="bg-white divide-y divide-gray-200">
        <% for (Contratlocation unite : AllContratlocation) { %>
        <tr>
            <td class="px-6 py-4 whitespace-nowrap"><%= unite.getDateDebut() %></td>
            <td class="px-6 py-4 whitespace-nowrap"><%= unite.getDateFin() %></td>
            <td class="px-6 py-4 whitespace-nowrap"><%= unite.getUnitelocationByUniteLocationId().getId() %></td>
            <td class="px-6 py-4 whitespace-nowrap"><%= unite.getLocataireByLocataireId().getId() %></td>
            <td class="px-6 py-4 whitespace-nowrap">
                <a href="Admin-servlet?action=updateContrat&contratId=<%= unite.getId() %>" class="px-4 py-2 cursor-pointer font-medium text-white bg-blue-600 rounded-md hover:bg-blue-500 focus:outline-none focus:shadow-outline-blue active:bg-blue-600 transition duration-150 ease-in-out">Edit</a>

            </td>
        </tr>
        <% } %>
    </tbody>
</table>

`,
        contact: `
      <table class="min-w-full divide-y divide-gray-200">
    <thead>
        <tr>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Adresse</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Description</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Equipements</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Image</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Action</th>
        </tr>
    </thead>
  <tbody class="bg-white divide-y divide-gray-200">
        <% for (Immeuble immeuble : ALLimmeubles) { %>
        <tr>
            <td class="px-6 py-4 whitespace-nowrap"><%= immeuble.getNom() %></td>
            <td class="px-6 py-4 whitespace-nowrap"><%= immeuble.getAdresse() %></td>
            <td class="px-6 py-4 whitespace-nowrap"><%= immeuble.getDescription() %></td>
            <td class="px-6 py-4 whitespace-nowrap"><%= immeuble.getEquipements() %></td>
            <td class="px-6 py-4 whitespace-nowrap">
                <img src="<%= immeuble.getImage() %>" alt="Image" class="w-20 h-20">
            </td>
            <td class="px-6 py-4 whitespace-nowrap">
                <a href="Admin-servlet?action=updateImm&immId=<%= immeuble.getId() %>" class="px-4 py-2 cursor-pointer font-medium text-white bg-blue-600 rounded-md hover:bg-blue-500 focus:outline-none focus:shadow-outline-blue active:bg-blue-600 transition duration-150 ease-in-out">Edit</a>
                <a href="Admin-servlet?action=deleteImm&immId=<%= immeuble.getId() %>" class="ml-2 px-4 cursor-pointer py-2 font-medium text-white bg-red-600 rounded-md hover:bg-red-500 focus:outline-none focus:shadow-outline-red active:bg-red-600 transition duration-150 ease-in-out">Delete</a>
            </td>
        </tr>
        <% } %>
    </tbody>
</table>
      `,
    };

    document.querySelectorAll('#sidebar a').forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            const page = e.target.getAttribute('data-page');
            content.innerHTML = pages[page];
            sidebar.classList.add('-translate-x-full');
        });
    });
</script>
</body>
</html>

