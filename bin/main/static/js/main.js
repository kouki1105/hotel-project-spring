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
var areaLinks = {};
for(var i = 1; i <= 47; i++) {
	areaLinks[i] = ["/prefectures/" + i]
};

$(document).ready(function() {
	$('#jmap').jmap({
		width: '100%',
		height: '450px',
		lineColor: '#EFE4A6',
		lineWidth: 2,
		showTextShadow: true,
		backgroundColor: '#6FCFDD',
		backgroundRadius: '0.5rem',
		backgroundPadding: '0.5rem',
		dividerColor: '#EFE4A6',
		showPrefectureName: true,
		showRoundedPrefecture: true,
		prefectureNameType: 'full',
		prefectureBackgroundColor: '#62B34C',
		prefectureBackgroundHoverColor: '#95A834',
		prefectureLineColor: '#EFE4A6',
		prefectureLineHoverColor: '#ffffff',
		prefectureLineWidth: '2px',
		prefectureLineGap: '0px',
		prefectureInnerLineWidth: '1px',
		prefectureInnerLineColor: '#EFE4A6',
		prefectureInnerLineType: 'outset',
		prefectureRadius: '15px',
		prefectureLineY: '2px',
		prefectureLineX: '2px',
		fontSize: '0.6rem',
		fontColor: '#fff',

		onSelect: function(e, data) {
			location.href = areaLinks[data.code];
		},

		areas: [
			{code: 1,name: "北海道"},
			{code: 2,name: "青森"},
			{code: 3,name: "岩手"},
			{code: 4,name: "宮城"},
			{code: 5,name: "秋田"},
			{code: 6,name: "山形"},
			{code: 7,name: "福島"},
			{code: 8,name: "茨城"},
			{code: 9,name: "栃木"},
			{code: 10,name: "群馬"},
			{code: 11,name: "埼玉"},
			{code: 12,name: "千葉"},
			{code: 13,name: "東京"},
			{code: 14,name: "神奈川"},
			{code: 15,name: "新潟"},
			{code: 16,name: "富山"},
			{code: 17,name: "石川"},
			{code: 18,name: "福井"},
			{code: 19,name: "山梨"},
			{code: 20,name: "長野"},
			{code: 21,name: "岐阜"},
			{code: 22,name: "静岡"},
			{code: 23,name: "愛知"},
			{code: 24,name: "三重"},
			{code: 25,name: "滋賀"},
			{code: 26,name: "京都"},
			{code: 27,name: "大阪"},
			{code: 28,name: "兵庫"},
			{code: 29,name: "奈良"},
			{code: 30,name: "和歌山"},
			{code: 31,name: "鳥取"},
			{code: 32,name: "島根"},
			{code: 33,name: "岡山"},
			{code: 34,name: "広島"},
			{code: 35,name: "山口"},
			{code: 36,name: "徳島"},
			{code: 37,name: "香川"},
			{code: 38,name: "愛媛"},
			{code: 39,name: "高知"},
			{code: 40,name: "福岡"},
			{code: 41,name: "佐賀"},
			{code: 42,name: "長崎"},
			{code: 43,name: "熊本"},
			{code: 44,name: "大分"},
			{code: 45,name: "宮崎"},
			{code: 46,name: "鹿児島"},
			{code: 47,name: "沖縄"}
			],
	});
});

// hotel
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
