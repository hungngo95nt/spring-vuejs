<template>
  <b-container>
    <b-row v-if="true" id="table">
      <vue-good-table
        :columns="columns"
        :rows="list"
        styleClass="vgt-table"
        :pagination-options="{
          enabled: true,
          mode: 'records',
          perPage: 5,
          position: 'down',
          perPageDropdown: [5, 10, 15],
          dropdownAllowAll: false,
          setCurrentPage: 2,
          nextLabel: 'Next',
          prevLabel: 'Prev',
          rowsPerPageLabel: 'Rows per page',
          ofLabel: 'of',
          pageLabel: 'page', // for 'pages' mode
          allLabel: 'All',
        }">
        />
      </vue-good-table>
     </b-row>
     
    <b-row>
      <b-col cols="4">
        <input type="file" id="input" v-on:change="test" class="btn btn-light"/>
      </b-col>
       <b-col cols="4">
         <button type="button" class="btn btn-primary" v-on:click="commit">Insert to DB</button>
      </b-col>
    </b-row>
     
  </b-container>
</template>

<script>
import readXlsxFile from "read-excel-file";
export default {
  name: "ExcelFile",

  data() {
    return {
      flag: false,
      myfile: [],
      input: null,
      list: [],
      columns: [
        {
          label: "Name",
          field: "name",
        },
        {
          label: "Address",
          field: "address",
        },
        {
          label: "Code",
          field: "code",
        },
      ],
    };
  },
  methods: {
    commit(){
      console.log(" conmit");
       this.$http.post("http://localhost:8090/company/commit", this.list)
      .then(response => {
        console.log(response.data);
      });
    }, 
    test() {
      const input = document.getElementById("input");
      readXlsxFile(input.files[0]).then((rows) => {
        rows.forEach((row) => {
          console.log(row);
          var name = row[0];
          var address = row[1];
          var code = row[2];
          var json = { name: name, address: address, code: code };
          this.list.push(json);
        });
        this.flag = true;
      });
    },
  },
};
</script>
<style scoped>
  #table{
    padding-left: 20px;
    min-width: 200px;
    margin-bottom: 50px;
  }
  th->td{
    min-width: 100px;
  }
  vue-good-table{
    min-width: 500px;
  }
</style>
