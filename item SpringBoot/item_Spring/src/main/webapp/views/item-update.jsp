<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.item_springdemo.item_spring.model.Item" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Update Item</title>

    <style>

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            min-height: 100vh;

            display: flex;
            justify-content: center;
            align-items: center;

            padding: 20px;

            font-family: Arial, sans-serif;
            background-color: #f3f4f6;
        }

        .container {
            width: 420px;
            padding: 30px;

            background-color: white;
            border-radius: 12px;

            box-shadow:
                    0 8px 25px rgba(0, 0, 0, 0.12);
        }

        h1 {
            margin-bottom: 10px;

            color: #222;
            text-align: center;
        }

        .item-id {
            margin-bottom: 25px;

            color: #666;
            text-align: center;
        }

        .form-group {
            margin-bottom: 18px;
        }

        label {
            display: block;
            margin-bottom: 7px;

            color: #333;
            font-weight: bold;
        }

        input {
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

        input:focus {
            border-color: #f59e0b;

            box-shadow:
                    0 0 0 3px rgba(245, 158, 11, 0.15);
        }

        .error-input {
            border-color: #dc2626;
        }

        .error-message {
            display: none;

            margin-bottom: 18px;
            padding: 10px;

            color: #dc2626;
            background-color: #fee2e2;

            border: 1px solid #fecaca;
            border-radius: 6px;

            text-align: center;
        }

        .update-button {
            width: 100%;
            padding: 12px;

            color: white;
            background-color: #f59e0b;

            border: none;
            border-radius: 6px;

            font-size: 16px;
            font-weight: bold;
            cursor: pointer;

            transition: background-color 0.2s;
        }

        .update-button:hover {
            background-color: #d97706;
        }

        .update-button:disabled {
            background-color: #fcd34d;
            cursor: not-allowed;
        }

        .back-link {
            display: block;

            margin-top: 20px;

            color: #2563eb;
            text-align: center;
            text-decoration: none;
            font-weight: bold;
        }

        .back-link:hover {
            text-decoration: underline;
        }

        @media (max-width: 500px) {

            .container {
                width: 100%;
                padding: 22px;
            }
        }

    </style>
</head>

<body>

<%
    String contextPath = request.getContextPath();

    Item item =
            (Item) request.getAttribute("item");

    if (item == null) {
        response.sendRedirect(contextPath + "/items");
        return;
    }
%>

<div class="container">

    <h1>Update Item</h1>

    <p class="item-id">
        Item ID: <%= item.getId() %>
    </p>

    <p id="errorMessage"
       class="error-message">
    </p>

    <form id="updateForm"
          action="<%= contextPath %>/items/update"
          method="post">

        <input type="hidden"
               name="id"
               value="<%= item.getId() %>">

        <div class="form-group">

            <label for="name">
                Item Name
            </label>

            <input type="text"
                   id="name"
                   name="name"
                   value="<%= item.getName() %>">

        </div>

        <div class="form-group">

            <label for="price">
                Item Price
            </label>

            <input type="number"
                   id="price"
                   name="price"
                   value="<%= item.getPrice() %>"
                   step="0.01"
                   min="0.01">

        </div>

        <div class="form-group">

            <label for="count">
                Item Count
            </label>

            <input type="number"
                   id="count"
                   name="count"
                   value="<%= item.getCount() %>"
                   min="1">

        </div>

        <button type="submit"
                id="updateButton"
                class="update-button">

            Update Item

        </button>

    </form>

    <a class="back-link"
       href="<%= contextPath %>/items">

        Back To Items

    </a>

</div>

<script>

    const updateForm =
        document.getElementById("updateForm");

    const nameInput =
        document.getElementById("name");

    const priceInput =
        document.getElementById("price");

    const countInput =
        document.getElementById("count");

    const errorMessage =
        document.getElementById("errorMessage");

    const updateButton =
        document.getElementById("updateButton");


    updateForm.addEventListener("submit", function (event) {

        clearErrors();

        const itemName =
            nameInput.value.trim();

        const itemPrice =
            Number(priceInput.value);

        const itemCount =
            Number(countInput.value);


        if (itemName.length < 3) {

            event.preventDefault();

            showError(
                nameInput,
                "Item name must contain at least 3 characters."
            );

            return;
        }


        if (priceInput.value === "" || itemPrice <= 0) {

            event.preventDefault();

            showError(
                priceInput,
                "Item price must be greater than zero."
            );

            return;
        }


        if (
            countInput.value === "" ||
            itemCount <= 0 ||
            !Number.isInteger(itemCount)
        ) {

            event.preventDefault();

            showError(
                countInput,
                "Item count must be a whole number greater than zero."
            );

            return;
        }


        nameInput.value = itemName;

        updateButton.disabled = true;
        updateButton.textContent = "Updating...";

    });


    function showError(input, message) {

        input.classList.add("error-input");

        errorMessage.textContent = message;
        errorMessage.style.display = "block";

        input.focus();
    }


    function clearErrors() {

        errorMessage.textContent = "";
        errorMessage.style.display = "none";

        nameInput.classList.remove("error-input");
        priceInput.classList.remove("error-input");
        countInput.classList.remove("error-input");
    }

</script>

</body>
</html>