<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="com.item_springdemo.item_spring.model.Item" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>All Items</title>

    <style>

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f3f4f6;
            color: #222;
            min-height: 100vh;
            padding: 40px 20px;
        }

        .container {
            max-width: 1000px;
            margin: auto;
            background-color: white;
            padding: 30px;
            border-radius: 12px;

            box-shadow:
                    0 8px 25px rgba(0, 0, 0, 0.10);
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            gap: 15px;
            margin-bottom: 25px;
        }

        h1 {
            color: #222;
        }

        .add-button {
            display: inline-block;
            padding: 11px 18px;

            color: white;
            background-color: #2563eb;

            border-radius: 6px;
            text-decoration: none;
            font-weight: bold;

            transition: background-color 0.2s;
        }

        .add-button:hover {
            background-color: #1d4ed8;
        }

        .search-container {
            margin-bottom: 20px;
        }

        .search-input {
            width: 100%;
            padding: 11px;

            border: 1px solid #cccccc;
            border-radius: 6px;

            font-size: 16px;
            outline: none;

            transition:
                    border-color 0.2s,
                    box-shadow 0.2s;
        }

        .search-input:focus {
            border-color: #2563eb;

            box-shadow:
                    0 0 0 3px rgba(37, 99, 235, 0.15);
        }

        .table-container {
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        thead {
            background-color: #1f2937;
            color: white;
        }

        th,
        td {
            padding: 14px;
            text-align: center;
            border-bottom: 1px solid #dddddd;
        }

        tbody tr:hover {
            background-color: #f9fafb;
        }

        .update-button {
            display: inline-block;
            padding: 8px 13px;

            color: white;
            background-color: #f59e0b;

            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;

            transition: background-color 0.2s;
        }

        .update-button:hover {
            background-color: #d97706;
        }

        .delete-button {
            padding: 8px 13px;

            color: white;
            background-color: #dc2626;

            border: none;
            border-radius: 5px;

            font-size: 14px;
            font-weight: bold;
            cursor: pointer;

            transition: background-color 0.2s;
        }

        .delete-button:hover {
            background-color: #b91c1c;
        }

        .empty-message,
        .not-found-message {
            padding: 25px;
            text-align: center;

            color: #555;
            background-color: #f9fafb;

            border: 1px solid #dddddd;
            border-radius: 7px;
        }

        .not-found-message {
            display: none;
            margin-top: 15px;
        }

        @media (max-width: 650px) {

            body {
                padding: 20px 10px;
            }

            .container {
                padding: 20px;
            }

            .header {
                flex-direction: column;
                align-items: stretch;
                text-align: center;
            }

            .add-button {
                text-align: center;
            }

            th,
            td {
                padding: 10px;
            }
        }

    </style>
</head>

<body>

<%
    String contextPath = request.getContextPath();

    List<Item> items =
            (List<Item>) request.getAttribute("items");
%>

<div class="container">

    <div class="header">

        <h1>All Items</h1>

        <a class="add-button"
           href="<%= contextPath %>/items/add">

            Add New Item

        </a>

    </div>

    <%
        if (items == null || items.isEmpty()) {
    %>

    <p class="empty-message">
        There are no items.
    </p>

    <%
        } else {
    %>

    <div class="search-container">

        <input type="text"
               id="searchInput"
               class="search-input"
               placeholder="Search by item name">

    </div>

    <div class="table-container">

        <table>

            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Count</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            </thead>

            <tbody id="itemsTableBody">

            <%
                for (Item item : items) {
            %>

            <tr class="item-row">

                <td>
                    <%= item.getId() %>
                </td>

                <td class="item-name">
                    <%= item.getName() %>
                </td>

                <td>
                    <%= item.getPrice() %>
                </td>

                <td>
                    <%= item.getCount() %>
                </td>

                <td>
                    <a class="update-button"
                       href="<%= contextPath %>/items/edit/<%= item.getId() %>">

                        Update

                    </a>
                </td>

                <td>
                    <form class="delete-form"
                          data-item-id="<%= item.getId() %>"
                          action="<%= contextPath %>/items/delete/<%= item.getId() %>"
                          method="post">

                        <button class="delete-button"
                                type="submit">

                            Delete

                        </button>

                    </form>
                </td>

            </tr>

            <%
                }
            %>

            </tbody>

        </table>

    </div>

    <p id="notFoundMessage"
       class="not-found-message">

        No items match your search.

    </p>

    <%
        }
    %>

</div>

<script>

    const searchInput =
        document.getElementById("searchInput");

    const itemRows =
        document.querySelectorAll(".item-row");

    const notFoundMessage =
        document.getElementById("notFoundMessage");

    const deleteForms =
        document.querySelectorAll(".delete-form");


    if (searchInput !== null) {

        searchInput.addEventListener("input", function () {

            const searchText =
                searchInput.value.trim().toLowerCase();

            let visibleItems = 0;

            itemRows.forEach(function (row) {

                const itemName =
                    row.querySelector(".item-name")
                        .textContent
                        .trim()
                        .toLowerCase();

                if (itemName.includes(searchText)) {

                    row.style.display = "";
                    visibleItems++;

                } else {

                    row.style.display = "none";
                }
            });


            if (visibleItems === 0) {

                notFoundMessage.style.display = "block";

            } else {

                notFoundMessage.style.display = "none";
            }

        });

    }


    deleteForms.forEach(function (deleteForm) {

        deleteForm.addEventListener("submit", function (event) {

            const itemId =
                deleteForm.getAttribute("data-item-id");

            const confirmed =
                confirm(
                    "Are you sure you want to delete item ID "
                    + itemId
                    + "?"
                );

            if (!confirmed) {
                event.preventDefault();
            }

        });

    });

</script>

</body>
</html>