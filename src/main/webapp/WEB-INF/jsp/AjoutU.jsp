<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="sn.dev.jee_locations_immeubles.Entities.Unitelocation" %>
<%@ page import="sn.dev.jee_locations_immeubles.Entities.Immeuble" %>
<%@ page import="java.util.List" %>
<%
    Unitelocation unit = (Unitelocation) request.getAttribute("unit");
    List<Immeuble> ALLimmeubles = (List<Immeuble>) request.getAttribute("ALLimmeubles");

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
