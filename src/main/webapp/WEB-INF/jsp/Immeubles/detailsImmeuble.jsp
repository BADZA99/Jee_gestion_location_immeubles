<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="sn.dev.jee_locations_immeubles.Entities.Unitelocation" %>
<%@ page import="sn.dev.jee_locations_immeubles.Entities.Immeuble" %>
<%@ page import="sn.dev.jee_locations_immeubles.dao.ImmeubleDao" %>
<%@ page import="java.util.List" %>
<jsp:include page="../../../header.jsp" />

<%
    List<Unitelocation> unitLocations = (List<Unitelocation>) request.getAttribute("uniteLocbyId");
    Immeuble currentImmeuble = (Immeuble) request.getAttribute("currentImmeuble");
%>

<div class="relative mx-auto max-w-screen-xl p-5 sm:p-10 md:p-16">
  <div class="grid grid-cols-1 gap-5 sm:grid-cols-12">
   <div class="sm:col-span-5">
      <a href="#">
          <div class="overflow-hidden h-48 bg-cover text-center" style="min-height: 300px;">
              <img class="w-full  object-cover" src="<%= request.getContextPath()+"/images/immeubles/"+ currentImmeuble.getImage() %>">
      </div> </a>
      <div class="mt-3 flex flex-col justify-between rounded-b bg-white leading-normal lg:rounded-b-none lg:rounded-r">

        <div class="">
          <a href="#" class="text-xs font-medium uppercase text-indigo-600 transition duration-500 ease-in-out hover:text-gray-900"> <%= currentImmeuble.getNom() %> </a>
          <a href="#" class="mb-2 block text-2xl font-bold text-gray-900 transition duration-500 ease-in-out hover:text-indigo-600"><%= currentImmeuble.getDescription() %></a>
          <p class="mt-2 text-base text-gray-700">Adresse: <%= currentImmeuble.getAdresse() %></p>
            <p class="mt-2 text-base text-gray-700">Equipements: <%= currentImmeuble.getEquipements() %></p>
        </div>
      </div>
    </div>
    <div class="grid grid-cols-2 gap-5 sm:col-span-7 lg:grid-cols-3">
      <% for (Unitelocation unit : unitLocations) { %>
        <div class="">
          <a href="#">
            <div class="h-40 overflow-hidden bg-cover text-center">
                <img class="w-full h-48 object-cover" src="<%= request.getContextPath()+"/images/uniteLocation/"+ unit.getImage() %>">
            </div>
          </a> 
          <a href="#" class="text-md my-2 inline-block font-semibold text-gray-900 transition duration-500 ease-in-out hover:text-indigo-600">
            <%= unit.getNumeroUnite() %>
          </a>
        </div>
      <% } %>
    </div>
  </div>
</div>