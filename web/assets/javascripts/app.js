var applyDatepickerMask = function(){
    $(".datepicker").inputmask("dd/mm/yyyy");
  };

  var applyMonthpickerMask = function(){
    $(".monthpicker").inputmask("mm/yyyy");
  };

  var applyTimeMask = function(){
    $(".time").inputmask("hh:mm");
  };

  var applyAreaCodeMask = function(){
    $('.area-code').inputmask('99');
  };

  var applyZipCodeMask = function(){
    $('.zip-code').inputmask('99.999-999');
  };

  var applyNumberMask = function(){
    $('.number').inputmask({alias: 'numeric', greedy : false, rightAlign: false});
  };

  var applyPercentageMask = function(){
    $('.percentage').inputmask({alias: 'percentage', greedy : false, rightAlign: false});
  };

  var applyFederalIdMask = function(){
    $('.federal-id').inputmask('999.999.999-99');
  };

  var applyCNPJMask = function(){
    $('.cnpj').inputmask('99.999.999/9999-99');
  };

  var applyCelphoneMask = function(){
    $('.cellphones').inputmask('[9]9999-9999', {greedy : false, numericInput: true});
  };

  var applyAgencyMask = function(){
    $('.agency').inputmask('9999');
  };

  var applyAccountMask = function(){
    $('.account').inputmask('99999[99999]');
  };

  var applyC1C2C3Mask = function(){
    $('.c1c2c3').inputmask('9');
  };

  var applyDVMask = function(){
    $('.dv').inputmask('*');
  };

  var applyCompMask = function(){
    $('.comp').inputmask('999');
  };


  Inputmask.extendAliases({
    "currency": {
      prefix: "R$ ",
      placeHolder: '0,0',
      autoUnmask: true,
      rightAlign: false
    }
  });

  var applyCurrencyMask = function(){
    $('.currency').inputmask({alias: 'currency'});
  };

  var applyEmailMask = function(){
    $('.email').inputmask({
      mask: "*{1,20}[.*{1,20}][.*{1,20}][.*{1,20}]@*{1,20}[.*{2,6}][.*{1,2}]",
      greedy: false,
      onBeforePaste: function (pastedValue, opts) {
        pastedValue = pastedValue.toLowerCase();
        return pastedValue.replace("mailto:", "");
      },
      definitions: {
        '*': {
          validator: "[0-9A-Za-z!#$%&'*+/=?^_`{|}~\-]",
          cardinality: 1,
          casing: "lower"
        }
      }
    });
  };
  
