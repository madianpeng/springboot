//查询赋值
function query(form){
    var id=$('#id').val();
    $.ajax({
        url: '/area/query',
        type:'post',
        data:{
        	parentid:'-1'
        },
        success:function (result) {
            if(result.state){
                var data_=result.data;            
                //省市区
                provinceChange(form,data_.province,data_.city,data_.county);
            }
        }
    });
}
 
/**
 * 初始化省
 */
function provinceChange(form,provinceValue,cityValue,countyValue){
    var ChineseDistricts=districts();
    var provinces=ChineseDistricts["86"];
    var province=$('#province');
    province.empty();
    province.append('<option value="'+""+'">'+"请选择省"+'</option>');
    for(key in provinces){
        if(key==provinceValue){
            province.append('<option selected value="'+key+'">'+provinces[key]+'</option>');
        }else{
            province.append('<option value="'+key+'">'+provinces[key]+'</option>');
        }
    }
    form.render('select');
    cityChange(form,provinceValue,cityValue,countyValue);
 
}
 
/**
 * 初始化市
 */
function cityChange(form,provinceValue,cityValue,countyValue){
    //改变市
    var ChineseDistricts=districts();
    var citys=ChineseDistricts[provinceValue];
    var city=$('#city');
    city.empty();
    city.append('<option value="'+""+'">'+"请选择市"+'</option>');
    for(key in citys){
        if(key==cityValue){
            city.append('<option selected value="'+key+'">'+citys[key]+'</option>');
        }else{
            city.append('<option value="'+key+'">'+citys[key]+'</option>');
        }
    }
    form.render('select');
    countyChange(form,cityValue,countyValue);
}
 
/**
 * 初始化县/区
 */
function countyChange(form,cityValue,countyValue){
    //改变县/区
    var ChineseDistricts=districts();
    var countys=ChineseDistricts[cityValue];
    var county=$('#county');
    county.empty();
    county.append('<option value="'+""+'">'+"请选择县/区"+'</option>');
    for(key in countys){
        if(key==countyValue){
            county.append('<option selected value="'+key+'">'+countys[key]+'</option>');
        }else{
            county.append('<option value="'+key+'">'+countys[key]+'</option>');
        }
    }
    form.render('select');
}
 
layui.use(['form'], function(){
    var $ = layui.$
        ,layer = layui.layer  
        ,form = layui.form
 
    //查询赋值
    query(form);
 
    /*监听省select*/
    form.on('select(province)', function(data){
        cityChange(form,data.value,"","")
    });
 
    /*监听市select*/
    form.on('select(city)', function(data){
        countyChange(form,data.value,"")
    });
});