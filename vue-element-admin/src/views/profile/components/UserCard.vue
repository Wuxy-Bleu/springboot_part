<template>
  <el-card style="margin-bottom:20px;">
    <div slot="header" class="clearfix">
      <span>About me</span>
    </div>

    <div class="users-profile">
      <div class="box-center">
        <pan-thumb :image="users.avatar" :height="'100px'" :width="'100px'" :hoverable="false">
          <div>Hello</div>
          {{ users.role }}
        </pan-thumb>
      </div>
      <div class="box-center">
        <div class="users-name text-center">{{ users.name }}</div>
        <div class="users-role text-center text-muted">{{ users.role | uppercaseFirst }}</div>
      </div>
    </div>

    <div class="users-bio">
      <div class="users-education users-bio-section">
        <div class="users-bio-section-header"><svg-icon icon-class="education" /><span>Education</span></div>
        <div class="users-bio-section-body">
          <div class="text-muted">
            JS in Computer Science from the University of Technology
          </div>
        </div>
      </div>

      <div class="users-skills users-bio-section">
        <div class="users-bio-section-header"><svg-icon icon-class="skill" /><span>Skills</span></div>
        <div class="users-bio-section-body">
          <div class="progress-item">
            <span>Vue</span>
            <el-progress :percentage="70" />
          </div>
          <div class="progress-item">
            <span>JavaScript</span>
            <el-progress :percentage="18" />
          </div>
          <div class="progress-item">
            <span>Css</span>
            <el-progress :percentage="12" />
          </div>
          <div class="progress-item">
            <span>ESLint</span>
            <el-progress :percentage="100" status="success" />
          </div>
        </div>
      </div>
    </div>
  </el-card>
</template>

<script>
import PanThumb from '@/components/PanThumb'

export default {
  components: { PanThumb },
  props: {
    users: {
      type: Object,
      default: () => {
        return {
          name: '',
          email: '',
          avatar: '',
          role: ''
        }
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.box-center {
  margin: 0 auto;
  display: table;
}

.text-muted {
  color: #777;
}

.users-profile {
  .users-name {
    font-weight: bold;
  }

  .box-center {
    padding-top: 10px;
  }

  .users-role {
    padding-top: 10px;
    font-weight: 400;
    font-size: 14px;
  }

  .box-social {
    padding-top: 30px;

    .el-table {
      border-top: 1px solid #dfe6ec;
    }
  }

  .users-follow {
    padding-top: 20px;
  }
}

.users-bio {
  margin-top: 20px;
  color: #606266;

  span {
    padding-left: 4px;
  }

  .users-bio-section {
    font-size: 14px;
    padding: 15px 0;

    .users-bio-section-header {
      border-bottom: 1px solid #dfe6ec;
      padding-bottom: 10px;
      margin-bottom: 10px;
      font-weight: bold;
    }
  }
}
</style>
