$(document).ready(function() {

	// var width = $(window).width();
	// console.log(width);
	// if(width > 767) {
	// 	$(".project").hover3d({
	// 	    selector: ".project__card"
	// 	});
	// }



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
  	    type: 'image' // this is default type
    });

    $('#aig').magnificPopup({type:'image'});



  $('.image-link').magnificPopup({type:'image'});

  $('#vanGallery').magnificPopup({
	    items: [
	      {src: 'assets/img/portfolio/vanoptics/dashboard.png'},
	      {src: 'assets/img/portfolio/vanoptics/order.png'},
	      {src: 'assets/img/portfolio/vanoptics/myaccount.png'},
	      {src: 'assets/img/portfolio/vanoptics/login.png'}
	    ],
	    gallery: {
	      enabled: true
	    },
	    type: 'image' // this is default type
	});

  $('#bnlGallery').magnificPopup({
	    items: [
	      {src: 'assets/img/portfolio/bnl/book_0.png'},
	      {src: 'assets/img/portfolio/bnl/book_1.png'},
	      {src: 'assets/img/portfolio/bnl/book_2.png'},
	      {src: 'assets/img/portfolio/bnl/book_3.png'},
	    ],
	    gallery: {
	      enabled: true
	    },
	    type: 'image' // this is default type
	});

  $('#bitcoinGallery').magnificPopup({
	    items: [
	      {src: 'assets/img/portfolio/bitcoin/b_1.png'},
	      {src: 'assets/img/portfolio/bitcoin/b_2.png'},
	      {src: 'assets/img/portfolio/bitcoin/b_3.png'},
	      {src: 'assets/img/portfolio/bitcoin/b_4.png'}
	    ],
	    gallery: {
	      enabled: true
	    },
	    type: 'image' // this is default type
	});

  $('#hammerGallery').magnificPopup({
	    items: [
	      {src: 'assets/img/portfolio/hammer/hammer_1.jpg'},
	      {src: 'assets/img/portfolio/hammer/hammer_2.jpg'},
	      {src: 'assets/img/portfolio/hammer/hammer_3.jpg'}
	    ],
	    gallery: {
	      enabled: true
	    },
	    type: 'image' // this is default type
	});

  comments = [  {title: 'Perth, WA, Australia', sub: 'photo by Seen'},
	            {title: 'Centosa, Singapore', sub: ''},
	            {title: 'Namboong, WA, Australia', sub: ''},
	            {title: 'Bangkok, Thailand', sub: ''},
	            {title: 'Agra, India', sub: 'A rainy day at Taj mahal'},
	            {title: 'Seoul, Korea', sub: ''},
	            {title: 'Tokyo, Japan', sub: 'Yes! I love playing piano.'} ];

  $('#mypicGallery').magnificPopup({
	    items: [
	      {src: 'assets/img/me/me_1.jpg'},
	      {src: 'assets/img/me/me_2.jpg'},
	      {src: 'assets/img/me/me_3.jpg'},
	      {src: 'assets/img/me/me_4.jpg'},
	      {src: 'assets/img/me/me_5.jpg'},
	      {src: 'assets/img/me/me_6.jpg'},
	      {src: 'assets/img/me/me_7.jpg'}
	    ],
	    gallery: {
	      enabled: true
	    },
	    image: {
	    	titleSrc: function(item) {
	    		var i = item.index;
				return '@ ' + comments[i].title + '<small>' + comments[i].sub + '</small>';
            }
	    },
	    type: 'image' // this is default type
	});

});
