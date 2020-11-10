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