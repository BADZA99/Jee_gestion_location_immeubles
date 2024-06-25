<%@ page import="java.util.List" %>
<%@ page import="sn.dev.jee_locations_immeubles.Entities.Unitelocation" %>
<%@ page import="sn.dev.jee_locations_immeubles.Entities.Locataire" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

<%
    Locataire connctedUser = (Locataire) session.getAttribute("user");
    List<Unitelocation> allUniteLocations = (List<Unitelocation>) request.getAttribute("AllUniteLocations");
    String status = (String) request.getAttribute("status");
%>
<div class="bg-gray-100">
           <input type="hidden" id="status" value="<%=status%>">
    <div class="h-screen flex overflow-hidden bg-gray-200">
        <!-- Sidebar -->
        <div class="absolute bg-gray-800 text-white w-56 min-h-screen overflow-y-auto sidebar transform -translate-x-full ease-in-out duration-300"
             id="sidebar">
            <div class="p-4">
                <h1 class="text-2xl font-semibold">Sidebar</h1>
                <ul class="mt-4">
                    <li class="mb-2"><a href="#" class="block hover:text-indigo-400" data-page="home">Home</a></li>
                    <li class="mb-2"><a href="#" class="block hover:text-indigo-400" data-page="about">Mes infos</a></li>
                    <li class="mb-2"><a href="#" class="block hover:text-indigo-400" data-page="SendDemandeLoc">demander location</a></li>
                    <li class="mb-2"><a href="#" class="block hover:text-indigo-400" data-page="services">Les Unites locations</a></li>
                </ul>
            </div>
        </div>

        <!-- Content -->
        <div class="flex-1 flex flex-col overflow-hidden">
            <!-- Navbar -->
            <div class="bg-white shadow">
                <div class="container mx-auto">
                    <div class="flex justify-between items-center py-4 px-2">
                        <h1 class="text-xl font-semibold">WELCOME Locataire <%=connctedUser.getNom()%></h1>
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

            </div>
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
             <form action="LocataireServlet" method="post" class="w-[60%] mx-auto">
            <div>
                 <input type="hidden" name="action" value="update">
                  <input type="hidden" name="idLoc" value="<%=connctedUser.getId()%>">
                   <input type="hidden" name="idUtilisateur" value="<%=connctedUser.getIdUtilisateur()%>">
                <label class="block font-semibold" for="nom">Nom</label>
                <input class="w-full shadow-inner bg-gray-100 rounded-lg placeholder-black text-2xl p-4 border-none block mt-1 w-full" id="nom" type="text" name="nom" required="required" autofocus="autofocus" value="<%=connctedUser.getNom()%>">
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
                    <%=connctedUser.getNom()%>
                </dd>
            </div>
            <div class="py-3 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                <dt class="text-sm font-medium text-gray-500">
                    Role
                </dt>
                <dd class="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                 LOCATAIRE
                </dd>
            </div>

        </dl>
    </div>
</div>
      `,
        services: `
      <div class="max-w-screen-xl mx-auto p-5 sm:p-10 md:p-16">

    <div class="border-b mb-5 flex justify-between text-sm">
        <div class="text-indigo-600 flex items-center pb-2 pr-2 border-b-2 border-indigo-600 uppercase">
            <svg class="h-6 mr-3" version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 455.005 455.005"
                style="enable-background:new 0 0 455.005 455.005;" xml:space="preserve">
                <g>
                    <path d="M446.158,267.615c-5.622-3.103-12.756-2.421-19.574,1.871l-125.947,79.309c-3.505,2.208-4.557,6.838-2.35,10.343 c2.208,3.505,6.838,4.557,10.343,2.35l125.947-79.309c2.66-1.675,4.116-1.552,4.331-1.432c0.218,0.12,1.096,1.285,1.096,4.428 c0,8.449-6.271,19.809-13.42,24.311l-122.099,76.885c-6.492,4.088-12.427,5.212-16.284,3.084c-3.856-2.129-6.067-7.75-6.067-15.423 c0-19.438,13.896-44.61,30.345-54.967l139.023-87.542c2.181-1.373,3.503-3.77,3.503-6.347s-1.323-4.974-3.503-6.347L184.368,50.615 c-2.442-1.538-5.551-1.538-7.993,0L35.66,139.223C15.664,151.815,0,180.188,0,203.818v4c0,23.63,15.664,52.004,35.66,64.595 l209.292,131.791c3.505,2.207,8.136,1.154,10.343-2.35c2.207-3.505,1.155-8.136-2.35-10.343L43.653,259.72 C28.121,249.941,15,226.172,15,207.818v-4c0-18.354,13.121-42.122,28.653-51.902l136.718-86.091l253.059,159.35l-128.944,81.196 c-20.945,13.189-37.352,42.909-37.352,67.661c0,13.495,4.907,23.636,13.818,28.555c3.579,1.976,7.526,2.956,11.709,2.956 c6.231,0,12.985-2.176,19.817-6.479l122.099-76.885c11.455-7.213,20.427-23.467,20.427-37.004 C455.004,277.119,451.78,270.719,446.158,267.615z"> </path>
                    <path d="M353.664,232.676c2.492,0,4.928-1.241,6.354-3.504c2.207-3.505,1.155-8.136-2.35-10.343l-173.3-109.126 c-3.506-2.207-8.136-1.154-10.343,2.35c-2.207,3.505-1.155,8.136,2.35,10.343l173.3,109.126 C350.916,232.303,352.298,232.676,353.664,232.676z"> </path>
                    <path d="M323.68,252.58c2.497,0,4.938-1.246,6.361-3.517c2.201-3.509,1.14-8.138-2.37-10.338L254.46,192.82 c-3.511-2.202-8.139-1.139-10.338,2.37c-2.201,3.51-1.14,8.138,2.37,10.338l73.211,45.905 C320.941,252.21,322.318,252.58,323.68,252.58z"> </path>
                    <path d="M223.903,212.559c-3.513-2.194-8.14-1.124-10.334,2.39c-2.194,3.514-1.124,8.14,2.39,10.334l73.773,46.062 c1.236,0.771,2.608,1.139,3.965,1.139c2.501,0,4.947-1.251,6.369-3.529c2.194-3.514,1.124-8.14-2.39-10.334L223.903,212.559z"> </path>
                    <path d="M145.209,129.33l-62.33,39.254c-2.187,1.377-3.511,3.783-3.503,6.368s1.345,4.983,3.54,6.348l74.335,46.219 c1.213,0.754,2.586,1.131,3.96,1.131c1.417,0,2.833-0.401,4.071-1.201l16.556-10.7c3.479-2.249,4.476-6.891,2.228-10.37 c-2.248-3.479-6.891-4.475-10.37-2.228l-12.562,8.119l-60.119-37.38l48.2-30.355l59.244,37.147l-6.907,4.464 c-3.479,2.249-4.476,6.891-2.228,10.37c2.249,3.479,6.894,4.476,10.37,2.228l16.8-10.859c2.153-1.392,3.446-3.787,3.429-6.351 c-0.018-2.563-1.344-4.94-3.516-6.302l-73.218-45.909C150.749,127.792,147.647,127.795,145.209,129.33z"> </path>
                    <path d="M270.089,288.846c2.187-3.518,1.109-8.142-2.409-10.329l-74.337-46.221c-3.518-2.188-8.143-1.109-10.329,2.409 c-2.187,3.518-1.109,8.142,2.409,10.329l74.337,46.221c1.232,0.767,2.601,1.132,3.953,1.132 C266.219,292.387,268.669,291.131,270.089,288.846z"> </path>
                    <path d="M53.527,192.864c-2.187,3.518-1.109,8.142,2.409,10.329l183.478,114.081c1.232,0.767,2.601,1.132,3.953,1.132 c2.506,0,4.956-1.256,6.376-3.541c2.187-3.518,1.109-8.142-2.409-10.329L63.856,190.455 C60.338,188.266,55.714,189.346,53.527,192.864z"> </path>
                </g>
            </svg>
            <a href="#" class="font-semibold inline-block">Nos unites locations</a>
        </div>
    </div>


<div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-10">
    <%
    if(allUniteLocations != null) {
        for(Unitelocation uniteLocation : allUniteLocations) {
    %>
        <div class="rounded overflow-hidden shadow-lg flex flex-col">
            <div class="relative">
                <img class="w-full" src=" <%= request.getContextPath()+"/images/uniteLocation/"+ uniteLocation.getImage() %>" alt="Image de l'unité">

            </div>
            <div class="px-6 py-4 mb-auto">
                <a href="#" class="font-medium text-lg inline-block hover:text-indigo-600 transition duration-500 ease-in-out inline-block mb-2">
                    <%= uniteLocation.getNumeroUnite() %>
                </a>
                <p class="text-gray-500 text-sm">
                    Nombre de pièces: <%= uniteLocation.getNombrePieces() %><br>
                    Superficie: <%= uniteLocation.getSuperficie() %> m²<br>
                    Loyer: <%= uniteLocation.getLoyer() %> fcfa/mois
                </p>
            </div>
              <a href="LocataireServlet?action=demandeLoc&idUniteLoc=<%= uniteLocation.getId() %>&idLocataire=<%= connctedUser.getId() %>" class="font-medium text-lg inline-block hover:text-indigo-600 transition duration-500 ease-in-out inline-block mb-2">
    demander contrat location
</a>

        </div>
    <% 
        }
    } 
    %>
</div>

</div>
      `,
        SendDemandeLoc: `
      <div class="min-h-screen bg-gray-100 flex items-center justify-center">
    <div class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 max-w-md w-full">
        <h1 class="text-center text-2xl font-bold mb-6">Demande Location</h1>
        <form action="LocataireServlet" method="post">
          <input type="hidden" name="action" value="sendDemand">
                  <input type="hidden" name="idLoc" value="<%=connctedUser.getId()%>">
                  //juste pour eviter une erreur
                       <input type="hidden" name="idUtilisateur" value="<%=connctedUser.getIdUtilisateur()%>">
            <div class="mb-4">
                <label for="unitLocation" class="block text-gray-700 text-sm font-bold mb-2">Sélectionnez une unité de location</label>
                <select id="unitLocation" name="uniteLocationId" class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5">
                    <option value="" disabled selected>Choisissez une unité de location</option>
                    <%

                        if (allUniteLocations != null) {
                            for (Unitelocation unit : allUniteLocations) {
                    %>
                        <option value="<%= unit.getId() %>"><%= unit.getNumeroUnite() %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
            <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline w-full" type="submit">
                Envoyer Demande
            </button>
        </form>
    </div>
</div>

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

