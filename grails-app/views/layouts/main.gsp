<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Ninja Badge Exchange" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'ninja-star.jpg')}" type="image/jpeg" />
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="grailsLogo">
            <img src="${resource(dir:'images',file:'ninja-star.jpg')}" width="100" height="100" alt="Ninja" border="0" />
            <img src="${resource(dir:'images',file:'ninja-star.jpg')}" width="125" height="125" alt="Badge" border="0" />
            <img src="${resource(dir:'images',file:'ninja-star.jpg')}" width="150" height="150" alt="Exchange" border="0" />
        </div>
        <g:layoutBody />
    </body>
</html>