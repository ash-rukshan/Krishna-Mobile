package lk.krishan_mobile.asset.item.controller;


import lk.krishan_mobile.asset.brand.service.BrandService;
import lk.krishan_mobile.asset.category.controller.CategoryRestController;
import lk.krishan_mobile.asset.common_asset.model.enums.LiveDead;
import lk.krishan_mobile.asset.item.entity.Item;
import lk.krishan_mobile.asset.item.entity.enums.ItemStatus;
import lk.krishan_mobile.asset.item.entity.enums.MainCategory;
import lk.krishan_mobile.asset.item.entity.enums.WarrantyPeriod;
import lk.krishan_mobile.asset.item.service.ItemService;
import lk.krishan_mobile.asset.item_color.service.ItemColorService;
import lk.krishan_mobile.asset.supplier_item.service.SupplierItemService;
import lk.krishan_mobile.util.interfaces.AbstractController;
import lk.krishan_mobile.util.service.MakeAutoGenerateNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;

@Controller
@RequestMapping( "/item" )
public class ItemController implements AbstractController< Item, Integer > {
  private final ItemService itemService;
  private final MakeAutoGenerateNumberService makeAutoGenerateNumberService;
  private final ItemColorService itemColorService;
  private final BrandService brandService;
  private final SupplierItemService supplierItemService;

  @Autowired
  public ItemController(ItemService itemService, MakeAutoGenerateNumberService makeAutoGenerateNumberService, ItemColorService itemColorService, BrandService brandService,SupplierItemService supplierItemService) {
    this.itemService = itemService;
    this.makeAutoGenerateNumberService = makeAutoGenerateNumberService;
    this.itemColorService = itemColorService;
    this.brandService = brandService;
    this.supplierItemService=supplierItemService;
  }

  private String commonThings(Model model, Item item, Boolean addState) {
    model.addAttribute("statuses", ItemStatus.values());
    model.addAttribute("item", item);
    model.addAttribute("addStatus", addState);
    model.addAttribute("itemColors", itemColorService.findAll());
    model.addAttribute("brands", brandService.findAll());
    model.addAttribute("warrantyPeriod", WarrantyPeriod.values());
    model.addAttribute("mainCategories", MainCategory.values());
    model.addAttribute("urlMainCategory", MvcUriComponentsBuilder
            .fromMethodName(CategoryRestController.class, "getCategoryByMainCategory", "")
            .build()
            .toString());
    return "item/addItem";
  }

  @GetMapping
  public String findAll(Model model) {
    model.addAttribute("items", itemService.findAll().stream()
            .filter(x -> LiveDead.ACTIVE.equals(x.getLiveDead()))
            .collect(Collectors.toList()));

    return "item/item";
  }

  @Override
  public String findById(Integer id, Model model) {
    return null;
  }

  @GetMapping( "/add" )
  public String addForm(Model model) {
    return commonThings(model, new Item(), true);
  }

  @PostMapping( value = {"/save", "/update"} )
  public String persist(Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
    if ( bindingResult.hasErrors() ) {
      return commonThings(model, item, true);
    }
    if ( item.getId() == null ) {
      item.setItemStatus(ItemStatus.JUSTENTERED);
      //if there is not item in db
      if ( itemService.lastItem() == null ) {
        //need to generate new one
        item.setCode("KMSI" + makeAutoGenerateNumberService.numberAutoGen(null).toString());

      } else {
        //if there is item in db need to get that item's code and increase its value
        String previousCode = itemService.lastItem().getCode().substring(4);
        item.setCode("KMSI" + makeAutoGenerateNumberService.numberAutoGen(previousCode).toString());

      }


    }
    itemService.persist(item);
    return "redirect:/item";
  }

  @GetMapping( "/edit/{id}" )

  public String edit(@PathVariable Integer id, Model model) {
    return commonThings(model, itemService.findById(id), false);
  }

  @GetMapping( "/delete/{id}" )
  public String delete(@PathVariable Integer id, Model model) {
    itemService.delete(id);
    return "redirect:/item";
  }

  @GetMapping( "/{id}" )
  public String view(@PathVariable Integer id, Model model) {
    model.addAttribute("itemDetail", itemService.findById(id));
    return "item/item-detail";
  }


  @GetMapping( "/bysuplier" )
  public String getItemnewString( Model model) {
    model.addAttribute("items", itemService.findAll());
    return "item/itemnew";
  }


 @PostMapping( "/bysuplier" )
  public String getSupplierbyItem(@ModelAttribute Item item, Model model) {
    model.addAttribute("items", itemService.findAll());
    model.addAttribute("suppliers",supplierItemService.findByItem(item));
    return "item/itemnew";
  }




}
