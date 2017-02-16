/**
 * 
 */

$(function () {	
	tab('#tab',0);	
});

function tab(e, num){
    var num = num || 0;
    alert("on load\n" + "num : " + num);
    alert('tab function');
    
    var menu = $(e).children();
    var con = $(e+'_con').children();
    var select = $(menu).eq(num);
    var i = num;

    select.addClass('on');
    con.eq(num).show();

    menu.click(function(){
    	alert("Click");
    	
        if(select!==null){
        	alert("select!==null");
            select.removeClass("on");
            con.eq(i).hide();
        }

        select = $(this);
        i = $(this).index();
        
        alert("index i : " + i);
        
        select.addClass('on');
        con.eq(i).show();
    });
}
