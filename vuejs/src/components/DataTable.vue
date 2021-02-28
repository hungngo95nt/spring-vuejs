<template>
  <b-container>
      <b-row>
        <b-col cols="5">
          <b-form-group
            label-cols-md
            label="Company Name"
            label-for="input-default"
          >
            <b-form-input
              label-cols-lg
              v-model="companyName"
              placeholder="Enter compnay name"
            ></b-form-input>
          </b-form-group>
        </b-col>
        <b-col cols="5">
          <b-form-group
            label-cols-sm
            label="City Name"
            label-for="input-default"
          >
          <b-form-select v-model="city" :options="cities"></b-form-select>
          </b-form-group>
        </b-col>
        <b-col cols="2">
          <b-form-group>
            <b-button variant="outline-primary"  v-on:click="search">Search</b-button>
          </b-form-group>
        </b-col>
  </b-row>
  <b-row>
   <b-col cols="12">
   <vue-good-table
      mode="remote"
      @on-page-change="onPageChange"
      @on-per-page-change="onPerPageChange"
      :totalRows="totalRecords"
      :pagination-options="{
                enabled: true,
                mode: 'records',
                perPage: 2,
                position: 'down',
                perPageDropdown: [2, 4, 6],
                dropdownAllowAll: false,
                setCurrentPage: 0,
                nextLabel: 'Next',
                prevLabel: 'Prev',
                rowsPerPageLabel: 'Rows per page',
                ofLabel: 'of',
                pageLabel: 'page', // for 'pages' mode
                allLabel: 'All',
              }"
      :rows="rows"
      :columns="columns"
    />
   </b-col>
  </b-row>
  </b-container>
</template>
<script>
export default {
  name: 'DataTable',
  data () {
    return {
      companyName: ' ',
      city: ' ',
      cities: [ { value: null, text: 'Please select an option' }],
      isLoading: false,
      columns: [
        {
          label: 'Company Id',
          field: 'id',
        },
        {
          label: 'Company Name',
          field: 'name',
        },
        {
          label: 'Comany Address',
          field: 'address',
        },
        {
          label: 'Comany Code',
          field: 'code',
        },
      ],
      rows: [],
      totalRecords: 0,
      serverParams: {
        page: 0,
        perPage: 2
      }
    };
  },
  methods: {
    updateParams (newProps) {
      console.log("newProps " + newProps);
      this.serverParams = Object.assign({}, this.serverParams, newProps);
    },

    onPageChange (params) {
      console.log("  onPageChange - params.currentPage" + params.currentPage);
      this.updateParams({ page: params.currentPage - 1 });
      this.loadItems();
    },

    onPerPageChange (params) {
      console.log(" onPerPageChange - params " + params);
      this.updateParams({ perPage: params.currentPerPage });
      this.loadItems();
    },
    // load items is what brings back the rows from server
    loadItems () {
      console.log(" loadItems - this.serverParams.page" + this.serverParams.page);
      this.$http.get("http://localhost:8090/company/paging/" + this.serverParams.page + "/" + this.serverParams.perPage)
        .then(response => {
          this.totalRecords = response.data.totalElements;
          this.rows = response.data.content;

        });
    },
    search() {
      this.rows = [];
      console.log(" call server to fetch data ");
      this.$http.get("http://localhost:8090/company/seach/" + 
        this.companyName + "/" + this.city + "/" + this.serverParams.page + "/" + this.serverParams.perPage)
      .then(response => {
        this.totalRecords = response.data.totalElements;
        this.rows = response.data.content;
      });
    }
  },
  created() {
     // Load list city to select options
     console.log(" token " + localStorage.getItem('token'));
      this.$http.get("http://localhost:8090/company/list/citiy")
      .then(response => {
        console.log(response.data);
        this.cities = response.data;
        console.log(this.cities);
      });
      this.$http.get("http://localhost:8090/company/paging/" + this.serverParams.page + "/" + this.serverParams.perPage)
      .then(response => {
        this.totalRecords = response.data.totalElements;
        this.rows = response.data.content;
      });
     
  }

}
</script>
<style scoped>
.header {
  margin-top: 50px;
  margin-bottom: 50px;
}
</style>
