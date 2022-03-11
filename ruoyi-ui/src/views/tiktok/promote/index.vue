<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!--<el-form-item label="抖音连接" prop="tiktokUrl">
        <el-input
          v-model="queryParams.tiktokUrl"
          placeholder="请输入抖音连接"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
      <el-form-item label="抖音用户" prop="tiktokUser">
        <el-input
          v-model="queryParams.tiktokUser"
          placeholder="请输入抖音用户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核状态" prop="auditStatus">
        <el-input
          v-model="queryParams.auditStatus"
          placeholder="请输入审核状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--<el-form-item label="抖音昵称" prop="tiktokNickname">
        <el-input
          v-model="queryParams.tiktokNickname"
          placeholder="请输入抖音昵称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用规则" prop="userRule">
        <el-input
          v-model="queryParams.userRule"
          placeholder="请输入使用规则"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关键词" prop="keyWord">
        <el-input
          v-model="queryParams.keyWord"
          placeholder="请输入关键词"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
      <!--<el-form-item label="用户id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="部门id" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入部门id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['tiktok:promote:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['tiktok:promote:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['tiktok:promote:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['tiktok:promote:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="promoteList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
     <!-- <el-table-column label="${comment}" align="center" prop="id"  />-->
      <el-table-column label="创建时间" align="center" prop="createTime" />
      <el-table-column label="抖音连接" align="center" prop="tiktokUrl" />
      <el-table-column label="抖音用户" align="center" prop="tiktokUser" />
      <el-table-column label="抖音昵称" align="center" prop="tiktokNickname" />
      <el-table-column label="使用规则" align="center" prop="userRule" />
      <el-table-column label="关键词" align="center" prop="keyWord" />
      <el-table-column label="审核状态" align="center" prop="auditStatus" />
      <el-table-column label="用户id" align="center" prop="userId" />
    <!--  <el-table-column label="部门id" align="center" prop="deptId" />-->
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['tiktok:promote:edit']"
          >修改</el-button>
         <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['tiktok:promote:remove']"
          >删除</el-button>-->
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改抖音营销记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="抖音连接" prop="tiktokUrl">
          <el-input v-model="form.tiktokUrl" placeholder="请输入抖音连接" />
        </el-form-item>
        <el-form-item label="抖音用户" prop="tiktokUser">
          <el-input v-model="form.tiktokUser" placeholder="请输入抖音用户" />
        </el-form-item>
        <el-form-item label="抖音昵称" prop="tiktokNickname">
          <el-input v-model="form.tiktokNickname" placeholder="请输入抖音昵称" />
        </el-form-item>
        <el-form-item label="使用规则" prop="userRule">
          <el-input v-model="form.userRule" placeholder="请输入使用规则" />
        </el-form-item>
        <el-form-item label="关键词" prop="keyWord">
          <el-input v-model="form.keyWord" placeholder="请输入关键词" />
        </el-form-item>

        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPromote, getPromote, delPromote, addPromote, updatePromote } from "@/api/tiktok/promote";

export default {
  name: "Promote",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 抖音营销记录表格数据
      promoteList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        tiktokUrl: null,
        tiktokUser: null,
        tiktokNickname: null,
        userRule: null,
        keyWord: null,
        auditStatus: null,
        userId: null,
        deptId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询抖音营销记录列表 */
    getList() {
      this.loading = true;
      listPromote(this.queryParams).then(response => {
        this.promoteList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        tiktokUrl: null,
        tiktokUser: null,
        tiktokNickname: null,
        userRule: null,
        keyWord: null,
        auditStatus: "0",
        userId: null,
        deptId: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加抖音营销记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPromote(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改抖音营销记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePromote(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPromote(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除抖音营销记录编号为"' + ids + '"的数据项？').then(function() {
        return delPromote(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('tiktok/promote/export', {
        ...this.queryParams
      }, `promote_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
