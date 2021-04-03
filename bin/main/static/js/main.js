'use strict';

// layout
$(function() {
	var _window = $(window),
	_navtop = $('.navbar-top'),
	imgBottom;
	
	_window.on('scroll',function(){
		imgBottom = $('.parallax-window').height();
		if(_window.scrollTop() > imgBottom){
			_navtop.addClass('transform');
		}
		else{
			_navtop.removeClass('transform');
		}
	});
	
	_window.trigger('scroll');
});

$(function(){
	var pagetop = $('#pagetop');
	var appeared = false;
	// 1000px スクロールしたらボタン表示
	$(window).scroll(function () {
		// console.log($(this).scrollTop());
		if ($(this).scrollTop() > 1000) {
			if (appeared == false) {
				appeared = true;
				pagetop.animate({
					bottom: "40px"
				}, 800);
			}
		} else {
			if (appeared) {
				appeared = false;
				pagetop.animate({
					bottom: "-70px"
				}, 800);
			}
		}
	});
	pagetop.click(function () {
		$('body, html').animate({ scrollTop: 0 }, 500); // 0.5秒でページトップへ移動
		return false;
	});
});

// prefecture
$(function(){

	var prefectureLinks = {};
	for(var i = 1; i <= 47; i++) {
		prefectureLinks[i] = ["prefectures/" + i]
	}

	var areas = [
		{"code": 1 , "name":"北海道地方", "color":"#ca93ea", "hoverColor":"#e0b1fb", "prefectures":[1]},
		{"code": 2 , "name":"東北地方",   "color":"#a7a5ea", "hoverColor":"#d6d4fd", "prefectures":[2,3,4,5,6,7]},
		{"code": 3 , "name":"関東地方",   "color":"#84b0f6", "hoverColor":"#c1d8fd", "prefectures":[8,9,10,11,12,13,14]},
		{"code": 4 , "name":"北陸・甲信越地方",   "color":"#52d49c", "hoverColor":"#93ecc5", "prefectures":[15,16,17,18,19,20]},
		{"code": 4 , "name":"東海地方",   "color":"#77e18e", "hoverColor":"#aff9bf", "prefectures":[21,22,23,24]},
		{"code": 6 , "name":"近畿地方",   "color":"#f2db7b", "hoverColor":"#f6e8ac", "prefectures":[25,26,27,28,29,30]},
		{"code": 7 , "name":"中国地方",   "color":"#f9ca6c", "hoverColor":"#ffe5b0", "prefectures":[31,32,33,34,35]},
		{"code": 8 , "name":"四国地方",   "color":"#fbad8b", "hoverColor":"#ffd7c5", "prefectures":[36,37,38,39]},
		{"code": 9 , "name":"九州地方",   "color":"#f7a6a6", "hoverColor":"#ffcece", "prefectures":[40,41,42,43,44,45,46]},
		{"code":10 , "name":"沖縄地方",   "color":"#ea89c4", "hoverColor":"#fdcae9", "prefectures":[47]}
	];

	$("#map-container").japanMap(
		{
			areas  : areas,
			selection : "prefecture",
			borderLineWidth: 0.25,
			drawsBoxLine : false,
			movesIslands : true,
			showsPrefectureName : true,
			width: 800,
			font : "MS Mincho",
			fontSize : 12,
			fontColor : "areaColor",
			fontShadowColor : "black",
			onSelect:function(data){
				location.href = prefectureLinks[data.code];
			},
		}
	);
});

// hotel
$(function(){
	var jscrollOption = {
		loadingHtml: 'Loading...', // 記事読み込み中の表示、画像等をHTML要素で指定することも可能
		autoTrigger: true, // 次の表示コンテンツの読み込みを自動( true )か、ボタンクリック( false )にする
		padding: 20, // autoTriggerがtrueの場合、指定したコンテンツの下から何pxで読み込むか指定
		nextSelector: 'a.jscroll-next', // 次に読み込むコンテンツのURLのあるa要素を指定
		contentSelector: '.jscroll' // 読み込む範囲を指定、指定がなければページごと丸っと読み込む
	}
	$('.jscroll').jscroll(jscrollOption);
});

$(document).ready(function(){
	$('.slider-for').slick({
		slidesToShow: 1,
		slidesToScroll: 1,
		arrows: true,
		fade: true,
		asNavFor: '.slider-nav',
		autoplay: true
	});
	$('.slider-nav').slick({
		slidesToShow: 3,
		slidesToScroll: 1,
		asNavFor: '.slider-for',
		centerMode: true,
		focusOnSelect: true,
		arrows: false
	});
});

// review
$(function(){
	$('.fa').on('mouseover', function(){
		var $this = $(this);
		$this.parent().nextAll().children('i').removeClass('fa-star').addClass( "fa-star-o" );
		$this.parent().prevAll().children('i').removeClass( "fa-star-o" ).addClass('fa-star');
		$this.removeClass( "fa-star-o" ).addClass('fa-star');
	});
	$('.fa').one('click',function(){
		var $this = $(this); $this.addClass('active').parent().siblings().children('i').removeClass('active');
	});
	$('.fa').on('mouseleave', function(){
		var select = $('.active');
		select.parent().nextAll().children('i').removeClass('fa-star').addClass('fa-star-o');
		select.parent().prevAll().children('i').removeClass('fa-star-o').addClass('fa-star');
		select.removeClass('fa-star-o').addClass('fa-star');
	});
});
// $(function(){
// 	$('.fa').on('mouseover', function(){
// 		var $this = $(this);
// 		$this.nextAll().removeClass('fa-star').addClass( "fa-star-o" );
// 		$this.prevAll().removeClass( "fa-star-o" ).addClass('fa-star');
// 		$this.removeClass( "fa-star-o" ).addClass('fa-star');
// 	});
// 	$('.fa').one('click',function(){
// 	var $this = $(this); $this.addClass('active').siblings().removeClass('active');
// 	});
// 	$('.fa').on('mouseleave', function(){
// 		var select = $('.active');
// 		select.nextAll().removeClass('fa-star').addClass('fa-star-o');
// 		select.prevAll().removeClass('fa-star-o').addClass('fa-star');
// 		select.removeClass('fa-star-o').addClass('fa-star');
// 	});
// });


{
	var elem = document.getElementById('customRange3');
	var target = document.getElementById('rating');
	var rangeValue = function (elem, target) {
		return function(evt){
			target.innerHTML = elem.value;
		}
	}
	elem.addEventListener('input', rangeValue(elem, target));
}
