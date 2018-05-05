/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$('.product-options tr').click(function () {
    $(this).children('td').children('input').prop('checked', true);

    $('.product-options tr').removeClass('selected');
    $(this).toggleClass('selected');

});

