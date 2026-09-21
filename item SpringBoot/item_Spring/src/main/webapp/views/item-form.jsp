<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Add Item</title>

    <style>

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f3f4f6;
            min-height: 100vh;

            display: flex;
            justify-content: center;
            align-items: center;
        }

        .container {
            background-color: white;
            width: 420px;
            padding: 30px;

            border-radius: 12px;

            box-shadow:
                    0 8px 25px rgba(0, 0, 0, 0.12);
        }

        h1 {
            text-align: center;
            color: #222;
            margin-bottom: 25px;
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
            border-color: #2563eb;

            box-shadow:
                    0 0 0 3px rgba(37, 99, 235, 0.15);
        }

        .error-input {
            border-color: #dc2626;
        }

        .error-message {
            display: none;

            color: #dc2626;
            background-color: #fee2e2;

            border: 1px solid #fecaca;
            border-radius: 6px;

            padding: 10px;
            margin-bottom: 18px;

            text-align: center;
        }

        .save-button {
            width: 100%;
            padding: 12px;

            border: none;
            border-radius: 6px;

            color: white;
            background-color: #2563eb;

            font-size: 16px;
            font-weight: bold;

            cursor: pointer;

            transition: background-color 0.2s;
        }

        .save-button:hover {
            background-color: #1d4ed8;
        }

        .save-button:disabled {
            background-color: #93c5fd;
            cursor: not-allowed;
        }

        .back-link {
            display: block;

            margin-top: 20px;
            text-align: center;

            color: #2563eb;
            text-decoration: none;
            font-weight: bold;
        }

        .back-link:hover {
            text-decoration: underline;
        }

        @media (max-width: 500px) {

            .container {
                width: 90%;
                padding: 22px;
            }
        }

    </style>
</head>

<body>

<%
    String contextPath = request.getContextPath();
%>

<div class="container">

    <h1>Add New Item</h1>

    <p id="errorMessage"
       class="error-message">
    </p>

    <form id="itemForm"
          action="<%= contextPath %>/items/save"
          method="post">

        <div class="form-group">

            <label for="name">
                Item Name
            </label>

            <input type="text"
                   id="name"
                   name="name"
                   placeholder="Enter item name">

        </div>

        <div class="form-group">

            <label for="price">
                Item Price
            </label>

            <input type="number"
                   id="price"
                   name="price"
                   step="0.01"
                   min="0.01"
                   placeholder="Enter item price">

        </div>

        <div class="form-group">

            <label for="count">
                Item Count
            </label>

            <input type="number"
                   id="count"
                   name="count"
                   min="1"
                   placeholder="Enter item count">

        </div>

        <button type="submit"
                id="saveButton"
                class="save-button">

            Save Item

        </button>

    </form>

    <a class="back-link"
       href="<%= contextPath %>/items">

        Show All Items

    </a>

</div>

<script>

    const itemForm =
        document.getElementById("itemForm");

    const nameInput =
        document.getElementById("name");

    const priceInput =
        document.getElementById("price");

    const countInput =
        document.getElementById("count");

    const errorMessage =
        document.getElementById("errorMessage");

    const saveButton =
        document.getElementById("saveButton");


    itemForm.addEventListener("submit", function (event) {

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

        saveButton.disabled = true;
        saveButton.textContent = "Saving...";

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