// 按照时区获取时间
function getLocalTime(i) {
    //参数i为时区值数字，比如北京为东八区则输进8,西5输入-5
    if (typeof i !== 'number')
        return;
    var d = new Date();
    //得到1970年一月一日到现在的秒数
    var len = d.getTime();
    //本地时间与GMT时间的时间偏移差
    var offset = d.getTimezoneOffset() * 60000;
    //得到现在的格林尼治时间
    var utcTime = len + offset;
    return new Date(utcTime + 3600000 * i);
}

// 获取北京时间
function getBJTime() {
    var ts = getLocalTime(8), pad = function (n) {
        return (n < 10 ? '0' : '') + n;
    };

    return {
        'Y': pad(ts.getFullYear()),
        'M': pad(ts.getMonth() + 1),
        'D': pad(ts.getDate()),
        'H': pad(ts.getHours()),
        'I': pad(ts.getMinutes()),
        'S': pad(ts.getSeconds())
    };
}

// 将时间转换成指定格式的字符串
function formatDate(date, formatter) {
    var pad = function (n) {
        return (n < 10 ? '0' : '') + n;
    };
    var dateStr = pad(date.getFullYear()) + '-' + pad((date.getMonth() + 1)) + '-' + pad(date.getDate());
    if ('yyyy-MM-dd HH:mm:ss' === formatter) {
        dateStr += (' ' + pad(date.getHours()) + ':' + pad(date.getMinutes()) + ':' + pad(date.getSeconds()));
    }
    return dateStr;
}

// 将时间转换成yyyy-MM-dd的字符串
function date2Str(date) {
    return formatDate(date, 'yyyy-MM-dd');
}

// 将时间转换成yyyy-MM-dd HH:mm:ss的字符串
function datetime2Str(date) {
    return formatDate(date, 'yyyy-MM-dd HH:mm:ss');
}

// 获取00:00:00时刻的时间
function date2Str0(date) {
    return date2Str(date) + ' 00:00:00';
}

// 获取23:59:59时刻的时间
function date2Str24(date) {
    return date2Str(date) + ' 23:59:59';
}

// 将CST格式的时间字符串转换为GMT格式的时间字符串
function dateStrCST2GMT(dateStr) {
    var dateStrArr = dateStr.trim().split(' ');
    return dateStrArr[0] + ' ' + dateStrArr[1] + ' ' + dateStrArr[2] + ' ' + dateStrArr[5] + ' ' + dateStrArr[3] + ' GMT+0800';
}

//将CST格式的时间字符串转换为GMT格式的时间
function dateCST2GMT(dateStr) {
    return new Date(Date.parse(dateStrCST2GMT(dateStr)));
}

/**
 * 设置 cookie
 */
var setCookie = function (name, value, expires) {
    if (value === null) {
        value = '';
        expires = -1;
    }
    // 设置 cookie 过期时间
    if (expires && (typeof expires == 'number' || expires.toUTCString)) {
        var date;
        if (typeof expires == 'number') {// expires 为 cookie 有效的天数
            date = new Date();
            date.setTime(date.getTime() + (expires * 24 * 60 * 60 * 1000));
        } else {// expires 为 cookie 到期的时间
            date = expires;
        }
        expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
    } else {
        expires = '';
    }
    document.cookie = [name, '=', encodeURIComponent(value), expires].join('');
};

// 获取 cookie
var getCookie = function (name) {
    if (document.cookie && document.cookie !== '') {
        var cookies = document.cookie.split('; ');
        for (var i = 0; i < cookies.length; i++) {
            if (cookies[i].substring(0, name.length + 1) === (name + '=')) {
                return decodeURIComponent(cookies[i].substring(name.length + 1));
            }
        }
    }
    return null;
}

// 删除 cookie
var deleteCookie = function(name) {
    setCookie(name, null);
}

// 千分化金额，保留pointPsti位小数
Number.prototype.formatNumber = function (pointPsti) {
    pointPsti = (pointPsti >= 0 && pointPsti <= 20) ? pointPsti : 2;
    if (isNaN(this))
        return Number(0).toFixed(pointPsti)
    var s = this;
    var h = '';
    if (Number(s) < 0) {
        s = (s + '').replace('-', '');
        h = '-';
    }
    s = parseFloat((s + '').replace(/[^\d.-]/g, '')).toFixed(pointPsti) + '';
    var l = s.split('.')[0].split('').reverse(), r = s.split('.')[1];
    t = '';
    for (i = 0; i < l.length; i++) {
        t += l[i] + ((i + 1) % 3 === 0 && (i + 1) !== l.length ? ',' : '');
    }
    var number = '';
    if (s.indexOf('.') >= 0) {
        number = h + t.split('').reverse().join('') + '.' + r;
    } else {
        number = h + t.split('').reverse().join('');
    }
    return number;
};

// 获取数组中指定元素的索引
Array.prototype.indexOf = function (val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] === val)
            return i;
    }
    return -1;
};

// 数组添加元素，已存在的不添加
Array.prototype.pushNoRepeat = function (val) {
    var index = this.indexOf(val);
    if (index === -1) {
        this.push(val);
    }
};

// 删除数组指定元素
Array.prototype.remove = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};