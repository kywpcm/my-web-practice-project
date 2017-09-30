$(document).ready(function() {

	// var width = $(window).width();
	// console.log(width);
	// if(width > 767) {
	// 	$(".project").hover3d({
	// 	    selector: ".project__card"
	// 	});
	// }


    commentsMIS = [ {title: 'For security reasons, data can not be shown.', sub: '© tsis'},
    	         {title: 'For security reasons, data can not be shown.', sub: '© tsis'},
    	         {title: 'For security reasons, data can not be shown.', sub: '© tsis'},
    	         {title: 'For security reasons, data can not be shown.', sub: '© tsis'} ];
    $('#mis').magnificPopup({
  	    items: [
  	      {src: 'img/projects/mis/img_mis_01.png'},
  	      {src: 'img/projects/mis/img_mis_02.png'},
  	      {src: 'img/projects/mis/img_mis_03.png'},
  	      {src: 'img/projects/mis/img_mis_04.png'}
  	    ],
  	    gallery: {
  	      enabled: true
  	    },
        image: {
	      titleSrc: function(item) {
              var i = item.index;
              return commentsMIS[i].title + '<small>' + commentsMIS[i].sub + '</small>';
          }
	    },
  	    type: 'image' // this is default type
    });

    commentsAIG = [ {title: 'AIG representative homepage', sub: '© American International Group'} ];
    $('#aig').magnificPopup({
        image: {
          titleSrc: function(item) {
              var i = item.index;
              return commentsAIG[i].title + '<small>' + commentsAIG[i].sub + '</small>';
          }
        },
        type:'image'
    });

    commentsTMS = [ {title: 'TMS(Tsis Mentoring Service) for Android', sub: ''},
    	         {title: 'TMS(Tsis Mentoring Service) for Android', sub: ''},
    	         {title: 'TMS(Tsis Mentoring Service) for Android', sub: ''},
    	         {title: 'TMS(Tsis Mentoring Service) for Android', sub: ''},
                 {title: 'TMS(Tsis Mentoring Service) for Android', sub: ''} ];
    $('#tms').magnificPopup({
  	    items: [
  	      {src: 'img/projects/tms/img_tms_01.png'},
  	      {src: 'img/projects/tms/img_tms_02.png'},
  	      {src: 'img/projects/tms/img_tms_03.png'},
  	      {src: 'img/projects/tms/img_tms_04.png'},
          {src: 'img/projects/tms/img_tms_05.png'}
  	    ],
  	    gallery: {
  	      enabled: true
  	    },
        image: {
	      titleSrc: function(item) {
              var i = item.index;
              return commentsTMS[i].title + '<small>' + commentsTMS[i].sub + '</small>';
          }
	    },
  	    type: 'image' // this is default type
    });


    commentsFastdraw = [ {title: 'Fast draw for Android', sub: ''},
        	         {title: 'Fast draw for Android', sub: ''},
        	         {title: 'Fast draw for Android', sub: ''},
        	         {title: 'Fast draw for Android', sub: ''} ];
    $('#fastdraw').magnificPopup({
  	    items: [
  	      {src: 'img/projects/fastdraw/img_fastdraw_main_02.png'},
  	      {src: 'img/projects/fastdraw/img_fastdraw_gun_1_01.png'},
  	      {src: 'img/projects/fastdraw/img_fastdraw_gun_1_02.png'},
  	      {src: 'img/projects/fastdraw/img_fastdraw_ceremony_01.png'}
  	    ],
  	    gallery: {
  	      enabled: true
  	    },
        image: {
	      titleSrc: function(item) {
              var i = item.index;
              return commentsFastdraw[i].title + '<small>' + commentsFastdraw[i].sub + '</small>';
          }
	    },
  	    type: 'image' // this is default type
    });


    commentsWeather = [ {title: 'Weather Clock Widget', sub: '© CORE TECHNOLOGY'},
        	         {title: 'Weather Clock Widget', sub: '© CORE TECHNOLOGY'},
        	         {title: 'Weather Clock Widget', sub: '© CORE TECHNOLOGY'},
        	         {title: 'Weather Clock Widget', sub: '© CORE TECHNOLOGY'} ];
    $('#weatherClockWidget').magnificPopup({
  	    items: [
  	      {src: 'img/projects/weatherClockWidget/img_weatherClockWidget_01.png'},
  	      {src: 'img/projects/weatherClockWidget/img_weatherClockWidget_02.png'},
  	      {src: 'img/projects/weatherClockWidget/img_weatherClockWidget_03.png'},
  	      {src: 'img/projects/weatherClockWidget/img_weatherClockWidget_04.png'}
  	    ],
  	    gallery: {
  	      enabled: true
  	    },
        image: {
	      titleSrc: function(item) {
              var i = item.index;
              return commentsWeather[i].title + '<small>' + commentsWeather[i].sub + '</small>';
          }
	    },
  	    type: 'image' // this is default type
    });
});

function popAlertElemText(obj) {

    alert($(obj).text() + ' will be added later.');

}
