import { Module } from '@nestjs/common';
import { AuthService } from './auth.service';
import { UserModule } from 'src/user/user.module';
import { SecurityModule } from 'src/security/security.module';

@Module({
  imports: [UserModule, SecurityModule],
  providers: [AuthService],
  exports: [AuthService],
})
export class AuthModule {}
