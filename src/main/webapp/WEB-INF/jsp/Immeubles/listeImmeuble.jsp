<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="sn.dev.jee_locations_immeubles.Entities.Immeuble" %>
<%@ page import="java.util.List" %>
<jsp:include page="../../../header.jsp" />
<div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 p-10 md:px-20">
    <%
        List<Immeuble> immeubles = (List<Immeuble>) request.getAttribute("immeubles");
        for (Immeuble immeuble : immeubles) {
    %>
        <div class="bg-white rounded-xl shadow-md overflow-hidden">
            <div class="relative">
           <img class="w-full h-48 object-cover" src="<%= request.getContextPath()+"/images/immeubles/"+ immeuble.getImage() %>">
                <div class="absolute top-0 right-0 bg-indigo-500 text-white font-bold px-2 py-1 m-2 rounded-md">
                      <a href="index-servlet?id=<%= immeuble.getId() %>">details</a>
                </div>
            </div>
            <div class="p-4">
                <div class="text-lg font-medium text-gray-800 mb-2"><%= immeuble.getNom() %></div>
                <p class="text-gray-500 text-sm"><%= immeuble.getDescription() %></p>
                <p class="text-gray-500 text-sm"><%= immeuble.getAdresse() %></p>
                <p class="text-gray-500 text-sm"><%= immeuble.getEquipements() %></p>
            </div>
        </div>
    <%
        }
    %>
</div>


<!-- <div class="flex w-30 space-x-4 p-3">
  <a href="http://" target="_blank" rel="noopener noreferrer" class="bg-red-500 p-2 rounded-md text-white">ajouter Immeuble</a>
</div> -->

<!-- <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 p-10 md:px-20">
    <c:forEach var="immeuble" items="${immeubles}">
        <div class="bg-white rounded-xl shadow-md overflow-hidden">
            <div class="relative">
                <img class="w-full h-48 object-cover" src="${immeuble.image}">
                <div class="absolute top-0 right-0 bg-indigo-500 text-white font-bold px-2 py-1 m-2 rounded-md">
                    <a href="detailsImmeuble.jsp?id=${immeuble.id}" target="_blank" rel="noopener noreferrer">details</a>
                </div>
            </div>
            <div class="p-4">
                <div class="text-lg font-medium text-gray-800 mb-2">${immeuble.nom}</div>
                <p class="text-gray-500 text-sm">${immeuble.description}</p>
                <p class="text-gray-500 text-sm">${immeuble.adresse}</p>
                <p class="text-gray-500 text-sm">${immeuble.eaquipements}</p>
            </div>
        </div>
    </c:forEach>
</div> -->



