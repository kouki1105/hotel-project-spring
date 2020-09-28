'use strict';

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